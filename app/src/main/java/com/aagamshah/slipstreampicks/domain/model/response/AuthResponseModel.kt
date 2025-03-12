package com.aagamshah.slipstreampicks.domain.model.response

data class AuthResponseModel (
    val message: String,
    val user: User,
    val token: String,
)

data class User (
    val id: String,
    val email: String,
    val username: String
)

