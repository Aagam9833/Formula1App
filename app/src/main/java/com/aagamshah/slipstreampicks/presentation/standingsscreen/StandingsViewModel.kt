package com.aagamshah.slipstreampicks.presentation.standingsscreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aagamshah.slipstreampicks.common.Constants
import com.aagamshah.slipstreampicks.common.Resource
import com.aagamshah.slipstreampicks.domain.model.response.ConstructorStandingModel
import com.aagamshah.slipstreampicks.domain.model.response.DriverStandingModel
import com.aagamshah.slipstreampicks.domain.usecase.ConstructorStandingUseCase
import com.aagamshah.slipstreampicks.domain.usecase.DriverStandingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StandingsViewModel @Inject constructor(
    private val driverStandingUseCase: DriverStandingUseCase,
    private val constructorStandingUseCase: ConstructorStandingUseCase,
) : ViewModel() {

    private val _isLoading = mutableStateOf<Boolean>(false)
    val isLoading: Boolean get() = _isLoading.value

    private val _driverStandingModel = mutableStateOf<DriverStandingModel?>(null)
    val driverStandingModel: DriverStandingModel? get() = _driverStandingModel.value

    private val _constructorStandingModel = mutableStateOf<ConstructorStandingModel?>(null)
    val constructorStandingModel: ConstructorStandingModel? get() = _constructorStandingModel.value

    init {
        callDriverStandingApi()
        callConstructorStandingApi()
    }

    private fun callDriverStandingApi() {
        viewModelScope.launch {
            driverStandingUseCase.invoke().onEach { result ->
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
                            _driverStandingModel.value = it
                        }
                    }
                }
            }.collect()
        }
    }

    private fun callConstructorStandingApi() {
        viewModelScope.launch {
            constructorStandingUseCase.invoke().onEach { result ->
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
                            _constructorStandingModel.value = it
                        }
                    }
                }
            }.collect()
        }
    }

}