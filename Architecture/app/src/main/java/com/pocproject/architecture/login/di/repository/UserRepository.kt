package com.pocproject.architecture.login.di.repository

import com.pocproject.architecture.login.Data.User
import kotlinx.coroutines.delay

class UserRepository {
    // Simulate user data stored in memory
    private val users = listOf(
        User("abcd@gmail.com", "abcd"),
        User("user@gmail.com", "user"),
    )

    suspend fun loginUser(email : String,password: String): Boolean {
        // Simulate a network delay
        delay(1000)

        // Check if the email and password match a user in the list
        return users.any { it.email == email && it.password == password }
    }
}