package com.aagamshah.splitstreampicks.presentation.mainscreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aagamshah.splitstreampicks.common.Constants
import com.aagamshah.splitstreampicks.common.Resource
import com.aagamshah.splitstreampicks.domain.model.NavigationModel
import com.aagamshah.splitstreampicks.domain.usecase.NavigationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigationUseCase: NavigationUseCase,
) : ViewModel() {

    private val _navigationModel = mutableStateOf<NavigationModel?>(null)
    val navigationModel: NavigationModel? get() = _navigationModel.value

    init {
        callNavigationApi()
    }

    private fun callNavigationApi() {
        viewModelScope.launch {
            navigationUseCase.invoke().onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        Log.d(Constants.TAG, result.message ?: "Something went wrong")
                    }

                    is Resource.Loading -> {
                        Log.d(Constants.TAG, "Loading")
                    }

                    is Resource.Success -> {
                        result.data?.let {
                            _navigationModel.value = it
                        }
                    }
                }
            }.collect()
        }
    }
}