package com.aagamshah.slipstreampicks.domain.usecase

import com.aagamshah.slipstreampicks.domain.model.local.User
import com.aagamshah.slipstreampicks.domain.repository.UserRepository
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User) = repository.insertUser(user)
}

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke() = repository.getUser()
}

class ClearUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke() = repository.clearUser()
}