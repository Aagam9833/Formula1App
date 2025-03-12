package com.aagamshah.slipstreampicks.presentation.splashscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aagamshah.slipstreampicks.data.local.sharedpreferences.PreferenceManager
import com.aagamshah.slipstreampicks.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userUseCase: GetUserUseCase,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    private val _isUserLoggedIn = mutableStateOf(false)
    val isUserLoggedIn: Boolean get() = _isUserLoggedIn.value

    init {
        viewModelScope.launch {
            val user = userUseCase.invoke()
            val token = preferenceManager.getAccessToken()
            _isUserLoggedIn.value = user != null && !token.isNullOrEmpty()
        }
    }
}