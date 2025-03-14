package com.aagamshah.slipstreampicks.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val id: String,
    val email: String,
    val username: String,
    val profileImage: String,
)

