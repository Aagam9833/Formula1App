package com.aagamshah.slipstreampicks.domain.model.request

data class SignUpRequestModel(
    val email: String,
    val username: String,
    val password: String,
)