package com.aagamshah.slipstreampicks.presentation.fantasyscreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aagamshah.slipstreampicks.domain.model.response.GetFantasyHomeResponseModel
import com.aagamshah.slipstreampicks.domain.usecase.FantasyHomeUseCase
import com.aagamshah.slipstreampicks.utils.Constants
import com.aagamshah.slipstreampicks.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FantasyViewModel @Inject constructor(
    private val fantasyHomeUseCase: FantasyHomeUseCase
) : ViewModel() {

    private val _isLoading = mutableStateOf<Boolean>(false)
    val isLoading: Boolean get() = _isLoading.value

    private val _homeData = mutableStateOf<GetFantasyHomeResponseModel?>(null)
    val homeData: GetFantasyHomeResponseModel? get() = _homeData.value

    init {
        getFantasyHome()
    }

    private fun getFantasyHome() {
        viewModelScope.launch {
            fantasyHomeUseCase.invoke().onEach { result ->
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
                            _homeData.value = it
                        }
                    }
                }
            }.collect()
        }
    }

}