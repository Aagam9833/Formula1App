package com.aagamshah.splitstreampicks.presentation.homescreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aagamshah.splitstreampicks.common.Constants
import com.aagamshah.splitstreampicks.common.Resource
import com.aagamshah.splitstreampicks.domain.model.DriverStandingModel
import com.aagamshah.splitstreampicks.domain.model.HomeModel
import com.aagamshah.splitstreampicks.domain.model.NextSession
import com.aagamshah.splitstreampicks.domain.usecase.DriverStandingUseCase
import com.aagamshah.splitstreampicks.domain.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
    private val driverStandingUseCase: DriverStandingUseCase,
) : ViewModel() {

    private val _homeModel = mutableStateOf<HomeModel?>(null)
    val homeModel: HomeModel? get() = _homeModel.value

    private val _driverStandingModel = mutableStateOf<DriverStandingModel?>(null)
    val driverStandingModel: DriverStandingModel? get() = _driverStandingModel.value

    private val _remainingTime = MutableStateFlow(CountdownState(0, 0, 0, false))
    val remainingTime: StateFlow<CountdownState> = _remainingTime.asStateFlow()
    private var countdownJob: Job? = null

    init {
        callHomeApi()
        callDriverStandingApi()
    }

    private fun callHomeApi() {
        viewModelScope.launch {
            homeUseCase.invoke().onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        Log.d(Constants.TAG, result.message ?: "Something went wrong")
                    }

                    is Resource.Loading -> {
                        Log.d(Constants.TAG, "Loading")
                    }

                    is Resource.Success -> {
                        result.data?.let {
                            _homeModel.value = it
                            startCountdown(it.nextSession)
                        }
                    }
                }
            }.collect()
        }
    }

    private fun callDriverStandingApi() {
        viewModelScope.launch {
            driverStandingUseCase.invoke().onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        Log.d(Constants.TAG, result.message ?: "Something went wrong")
                    }

                    is Resource.Loading -> {
                        Log.d(Constants.TAG, "Loading")
                    }

                    is Resource.Success -> {
                        result.data?.let {
                            _driverStandingModel.value = it
                        }
                    }
                }
            }.collect()
        }
    }

    private fun startCountdown(session: NextSession) {
        val targetTime = parseSessionTime(session.date, session.time)

        countdownJob?.cancel()
        countdownJob = viewModelScope.launch {
            while (true) {
                val now = Instant.now()
                val duration = Duration.between(now, targetTime).coerceAtLeast(Duration.ZERO)

                if (duration.isZero) {
                    _remainingTime.value = CountdownState(0, 0, 0, false)
                    countdownJob?.cancel()
                    countdownJob = null
                    break
                }

                val totalHours = duration.toHours()
                val days = totalHours / 24
                val hours = totalHours % 24
                val minutes = duration.toMinutes() % 60
                val seconds = duration.seconds % 60

                _remainingTime.value = if (totalHours > 99) {
                    CountdownState(days, hours, minutes, showDays = true)
                } else {
                    CountdownState(totalHours, minutes, seconds, showDays = false)
                }

                delay(if (totalHours > 99) 60 * 1000L else 1000L)
            }
        }
    }

    private fun parseSessionTime(date: String, time: String): Instant {
        val formatter = DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneOffset.UTC)
        return Instant.from(formatter.parse("${date}T${time}"))
    }

}