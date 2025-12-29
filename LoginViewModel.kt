package com.example.appdevsquad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginUiState(
  val email: String = "",
  val password: String = "",
  val isLoading: Boolean = false,
  val error: String? = null,
  val success: Boolean = false
)

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val repo: AuthRepository
) : ViewModel() {

  private val _uiState = MutableStateFlow(LoginUiState())
  val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

  fun onEmailChange(email: String): Unit = _uiState.update { it.copy(email = email, error = null) }
  fun onPasswordChange(password: String): Unit = _uiState.update { it.copy(password = password, error = null) }

  fun login() {
    val email = _uiState.value.email.trim()
    val password = _uiState.value.password
    if (email.isEmpty() || password.isEmpty()) {
      _uiState.update { it.copy(error = "Preencha todos os campos") }
      return
    }

    viewModelScope.launch {
      _uiState.update { it.copy(isLoading = true, error = null) }
      try {
        val ok = repo.login(email, password)
        if (ok) {
          _uiState.update { it.copy(isLoading = false, success = true) }
        } else {
          _uiState.update { it.copy(isLoading = false, error = "Credenciais inválidas") }
        }
      } catch (e: Exception) {
        _uiState.update { it.copy(isLoading = false, error = e.message ?: "Ocorreu um erro desconhecido") }
      }
    }
  }
}