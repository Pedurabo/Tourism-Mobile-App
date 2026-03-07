package com.example.tourism.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: String,
    val fullName: String,
    val email: String,
    val phoneNumber: String,
    val password: String = "password123", // Default for simulation
    val profileImageUrl: String? = null,
    val userType: UserType = UserType.TOURIST
)

enum class UserType {
    TOURIST,
    TOUR_OPERATOR,
    ADMIN
}
