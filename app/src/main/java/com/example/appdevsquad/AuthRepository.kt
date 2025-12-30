package com.example.appdevsquad

import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

// User data model
data class User(val email: String, val pass: String)

interface AuthRepository {
    suspend fun login(email: String, pass: String): Boolean
    suspend fun register(email: String, pass: String): Result<Unit>
}

@Singleton
class FakeAuthRepository @Inject constructor() : AuthRepository {

    private val users = mutableListOf(User("admin", "1234"))

    override suspend fun login(email: String, pass: String): Boolean {
        delay(800) // simulating network
        return users.any { it.email == email && it.pass == pass }
    }

    override suspend fun register(email: String, pass: String): Result<Unit> {
        delay(800) // simulating network
        if (users.any { it.email == email }) {
            return Result.failure(Exception("Este e-mail já está em uso."))
        }
        users.add(User(email, pass))
        return Result.success(Unit)
    }
}