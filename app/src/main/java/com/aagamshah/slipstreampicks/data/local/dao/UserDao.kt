package com.aagamshah.slipstreampicks.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aagamshah.slipstreampicks.data.local.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun getUser(): UserEntity?

    @Query("DELETE FROM user")
    suspend fun clearUser()

    @Query("UPDATE user SET username = :username")
    suspend fun updateUsername(username: String)

    @Query("UPDATE user SET email = :email")
    suspend fun updateEmail(email: String)

    @Query("UPDATE user SET profileImage = :imageUrl")
    suspend fun updateProfileImage(imageUrl: String)

}
