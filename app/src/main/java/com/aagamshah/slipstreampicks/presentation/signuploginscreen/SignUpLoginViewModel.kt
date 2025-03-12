package com.aagamshah.slipstreampicks.presentation.signuploginscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aagamshah.slipstreampicks.utils.Resource
import com.aagamshah.slipstreampicks.domain.model.request.LoginRequestModel
import com.aagamshah.slipstreampicks.domain.model.request.SignUpRequestModel
import com.aagamshah.slipstreampicks.domain.usecase.LoginUseCase
import com.aagamshah.slipstreampicks.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpLoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val signUpUseCase: SignUpUseCase,
) : ViewModel() {

    private val _isLoading = mutableStateOf<Boolean>(false)
    val isLoading: Boolean get() = _isLoading.value

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: String? get() = _errorMessage.value

    fun loginValidations(
        identifier: String,
        password: String,
        onSuccess: () -> Unit
    ) {
        when {
            identifier.isBlank() -> {
                _errorMessage.value = "Please enter username or email"
                return
            }

            password.length < 8 -> {
                _errorMessage.value = "Password cannot be less than 8 characters"
                return
            }
        }
        viewModelScope.launch {
            loginUseCase.invoke(LoginRequestModel(identifier, password)).onEach { result ->
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

    fun signUpValidations(
        username: String,
        email: String,
        password: String,
        onSuccess: () -> Unit
    ) {
        when {
            username.isBlank() -> _errorMessage.value = "Username cannot be empty"
            !email.isValidEmail() -> _errorMessage.value = "Invalid email format"
            password.length < 8 -> _errorMessage.value =
                "Password must be at least 8 characters long"
        }
        viewModelScope.launch {
            signUpUseCase.invoke(SignUpRequestModel(email, username, password)).onEach { result ->
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

    private fun String.isValidEmail(): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    fun dismissError() {
        _errorMessage.value = null
    }


}