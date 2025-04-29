package com.aagamshah.slipstreampicks.domain.repository

import com.aagamshah.slipstreampicks.domain.model.response.MessageResponseModel

interface ForgotPasswordRepository {

    suspend fun forgotPassword(email: String): MessageResponseModel

    suspend fun resetPassword(email: String, otp: Int, password: String): MessageResponseModel

}