package com.aagamshah.slipstreampicks.presentation.forgotpasswordscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aagamshah.slipstreampicks.domain.usecase.ForgotPasswordUseCase
import com.aagamshah.slipstreampicks.utils.Resource
import com.aagamshah.slipstreampicks.utils.StringExtensions.isValidEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val forgotPasswordUseCase: ForgotPasswordUseCase
) : ViewModel() {

    private val _isLoading = mutableStateOf<Boolean>(false)
    val isLoading: Boolean get() = _isLoading.value

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: String? get() = _errorMessage.value

    fun forgotPassword(
        email: String,
        onSuccess: () -> Unit
    ) {

        when {
            email.trim().replace(" ", "").isBlank() -> {
                _errorMessage.value =
                    "Username cannot be empty"
                return
            }

            !email.isValidEmail() -> {
                _errorMessage.value = "Invalid email format"
                return
            }
        }

        viewModelScope.launch {
            forgotPasswordUseCase.invoke(email).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _isLoading.value = false
                        _errorMessage.value = result.message ?: "Something went wrong"
                    }

                    is Resource.Loading -> {
                        _isLoading.value = true
                    }

                    is Resource.Success -> {
                        _isLoading.value = false
                        onSuccess()
                    }
                }
            }.collect()
        }

    }

    fun resetPassword(
        email: String,
        otp: String,
        newPassword: String,
        confirmPassword: String,
        onSuccess: () -> Unit
    ) {
        when {
            otp.trim().replace(" ", "").isBlank() -> {
                _errorMessage.value =
                    "OTP cannot be empty"
                return
            }

            newPassword.trim().replace(" ", "").isBlank() -> {
                _errorMessage.value =
                    "New password cannot be empty"
                return
            }

            confirmPassword.trim().replace(" ", "").isBlank() -> {
                _errorMessage.value =
                    "Confirm password cannot be empty"
                return
            }

            newPassword != confirmPassword -> {
                _errorMessage.value =
                    "Passwords do not match"
                return
            }
        }
        viewModelScope.launch {
            forgotPasswordUseCase.invoke(email, otp.toInt(), newPassword).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _isLoading.value = false
                        _errorMessage.value = result.message ?: "Something went wrong"
                    }

                    is Resource.Loading -> {
                        _isLoading.value = true
                    }

                    is Resource.Success -> {
                        _isLoading.value = false
                        onSuccess()
                    }
                }
            }.collect()
        }

    }

    fun dismissError() {
        _errorMessage.value = null
    }

}