package com.aagamshah.slipstreampicks.presentation.raceresultscreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aagamshah.slipstreampicks.utils.Constants
import com.aagamshah.slipstreampicks.utils.Resource
import com.aagamshah.slipstreampicks.domain.model.response.RaceResultModel
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

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: String? get() = _errorMessage.value

    fun getRoundResults(roundNumber: String) {
        viewModelScope.launch {
            raceResultUseCase.invoke(roundNumber).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _isLoading.value = false
                        _errorMessage.value = result.message
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

    fun dismissError(){
        _errorMessage.value = null
    }

}