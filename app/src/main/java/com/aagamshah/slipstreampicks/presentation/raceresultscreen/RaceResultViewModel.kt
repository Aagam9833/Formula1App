package com.aagamshah.slipstreampicks.presentation.raceresultscreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aagamshah.slipstreampicks.common.Constants
import com.aagamshah.slipstreampicks.common.Resource
import com.aagamshah.slipstreampicks.domain.model.RaceResultModel
import com.aagamshah.slipstreampicks.domain.usecase.RaceResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RaceResultViewModel @Inject constructor(
    private val raceResultUseCase: RaceResultUseCase,
) : ViewModel() {

    private val _isLoading = mutableStateOf<Boolean>(false)
    val isLoading: Boolean get() = _isLoading.value

    private val _raceResultModel = mutableStateOf<RaceResultModel?>(null)
    val raceResultModel: RaceResultModel? get() = _raceResultModel.value

    fun getRoundResults(roundNumber: String) {
        viewModelScope.launch {
            raceResultUseCase.invoke(roundNumber).onEach { result ->
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
                            _raceResultModel.value = it
                        }
                    }
                }
            }.collect()
        }
    }


}