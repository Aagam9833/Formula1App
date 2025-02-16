package com.aagamshah.splitstreampicks.presentation.homescreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aagamshah.splitstreampicks.common.Constants
import com.aagamshah.splitstreampicks.common.Resource
import com.aagamshah.splitstreampicks.domain.model.HomeModel
import com.aagamshah.splitstreampicks.domain.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
) : ViewModel() {

    private val _homeModel = mutableStateOf<HomeModel?>(null)
    val homeModel: HomeModel? get() = _homeModel.value

    init {
        callHomeApi()
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
                        }
                    }
                }
            }.collect()
        }
    }

}