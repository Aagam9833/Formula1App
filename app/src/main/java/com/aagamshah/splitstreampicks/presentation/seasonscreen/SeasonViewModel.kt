package com.aagamshah.splitstreampicks.presentation.seasonscreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aagamshah.splitstreampicks.common.Constants
import com.aagamshah.splitstreampicks.common.Resource
import com.aagamshah.splitstreampicks.domain.model.CurrentSeasonModel
import com.aagamshah.splitstreampicks.domain.model.SessionDetail
import com.aagamshah.splitstreampicks.domain.usecase.CurrentSeasonUseCase
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
                        Log.d(Constants.TAG, result.message ?: "Something went wrong")
                    }

                    is Resource.Loading -> {
                        Log.d(Constants.TAG, "Loading")
                    }

                    is Resource.Success -> {
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