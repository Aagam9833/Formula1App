package com.aagamshah.slipstreampicks.domain.repository

import com.aagamshah.slipstreampicks.domain.model.local.User

interface UserRepository {
    suspend fun insertUser(user: User)
    suspend fun getUser(): User?
    suspend fun clearUser()

}