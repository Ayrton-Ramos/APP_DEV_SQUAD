package com.example.appdevsquad

import kotlinx.coroutines.delay
import javax.inject.Inject

interface AuthRepository {
  suspend fun login(email: String, password: String): Boolean
}

class FakeAuthRepository @Inject constructor(): AuthRepository {
  override suspend fun login(email: String, password: String): Boolean {
    delay(800) // simulando rede
    return (email == "admin" && password == "1234")
  }
} 