package com.aagamshah.slipstreampicks.data.repositoryimpl

import com.aagamshah.slipstreampicks.data.local.dao.UserDao
import com.aagamshah.slipstreampicks.data.local.entity.UserEntity
import com.aagamshah.slipstreampicks.domain.model.local.User
import com.aagamshah.slipstreampicks.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun insertUser(user: User) {
        userDao.insertUser(user.toEntity())
    }

    override suspend fun getUser(): User? {
        return userDao.getUser()?.toDomain()
    }

    override suspend fun clearUser() {
        userDao.clearUser()
    }

    private fun UserEntity.toDomain() = User(id, email, username)
    private fun User.toEntity() = UserEntity(id, email, username)

}
