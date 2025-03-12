package com.aagamshah.slipstreampicks.presentation.seasonscreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aagamshah.slipstreampicks.utils.Constants
import com.aagamshah.slipstreampicks.utils.Resource
import com.aagamshah.slipstreampicks.domain.model.response.CurrentSeasonModel
import com.aagamshah.slipstreampicks.domain.model.response.SessionDetail
import com.aagamshah.slipstreampicks.domain.usecase.CurrentSeasonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SeasonViewModel @Inject constructor(
    private val currentSeasonUseCase: CurrentSeasonUseCase,
) : ViewModel() {

    private val _isLoading = mutableStateOf<Boolean>(false)
    val isLoading: Boolean get() = _isLoading.value

    private val _currentSeasonModel = mutableStateOf<CurrentSeasonModel?>(null)
    val currentSeasonModel: CurrentSeasonModel? get() = _currentSeasonModel.value

    init {
        getCurrentSeason()
    }

    private fun getCurrentSeason() {
        viewModelScope.launch {
            currentSeasonUseCase.invoke().onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _isLoading.value = false
                        Log.d(Constants.TAG, result.message ?: "Something went wrong")
                    }

                    is Resource.Loading -> {
                        _isLoading.value = true
                        Log.d(Constants.TAG, "Loading")
                    }

                    is Resource.Success -> {
                        _isLoading.value = false
                        result.data?.let {
                            _currentSeasonModel.value = it
                        }
                    }
                }
            }.collect()
        }
    }

    fun formatDate(firstPractice: SessionDetail, race: SessionDetail): String {
        val startDate = firstPractice.date.substring(8, 10)
        val endDate = race.date.substring(8, 10)
        return "$startDate-$endDate"
    }

    fun formatMonth(firstPractice: SessionDetail, race: SessionDetail): String {
        val firstMonth = LocalDate.parse(firstPractice.date).month.getDisplayName(
            TextStyle.SHORT,
            Locale.ENGLISH
        )
        val raceMonth =
            LocalDate.parse(race.date).month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)

        return if (firstMonth == raceMonth) {
            firstMonth
        } else {
            "$firstMonth-$raceMonth"
        }
    }


}