package com.aagamshah.slipstreampicks.domain.model.request

data class ResetPasswordModel(
    val email: String,
    val otp: Int,
    val password: String,
)
