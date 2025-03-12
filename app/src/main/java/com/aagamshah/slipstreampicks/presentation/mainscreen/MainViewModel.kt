package com.aagamshah.slipstreampicks.presentation.mainscreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aagamshah.slipstreampicks.utils.Constants
import com.aagamshah.slipstreampicks.utils.Resource
import com.aagamshah.slipstreampicks.domain.model.response.NavigationModel
import com.aagamshah.slipstreampicks.domain.usecase.NavigationUseCase
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

    private val _isLoading = mutableStateOf<Boolean>(false)
    val isLoading: Boolean get() = _isLoading.value

    init {
        callNavigationApi()
    }

    private fun callNavigationApi() {
        viewModelScope.launch {
            navigationUseCase.invoke().onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _isLoading.value = false
                        Log.d(Constants.TAG, result.message ?: "Something went wrong")
                    }

                    is Resource.Loading -> {
                        _isLoading.value = true
                    }

                    is Resource.Success -> {
                        _isLoading.value = false
                        result.data?.let {
                            _navigationModel.value = it
                        }
                    }
                }
            }.collect()
        }
    }
}