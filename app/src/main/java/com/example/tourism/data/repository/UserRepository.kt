package com.example.tourism.data.repository

import com.example.tourism.data.local.UserDao
import com.example.tourism.data.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    
    suspend fun login(email: String, password: String): User? {
        val user = userDao.getUserByEmail(email)
        return if (user != null && user.password == password) {
            user
        } else {
            null
        }
    }

    suspend fun register(user: User) {
        userDao.insertUser(user)
    }

    fun getUserById(userId: String): Flow<User?> {
        return userDao.getUserById(userId)
    }

    suspend fun updateProfile(user: User) {
        userDao.updateUser(user)
    }
}
