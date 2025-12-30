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

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val companyName: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null
)

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repo: AuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onNameChange(name: String) = _uiState.update { it.copy(name = name, error = null) }
    fun onEmailChange(email: String) = _uiState.update { it.copy(email = email, error = null) }
    fun onPhoneChange(phone: String) = _uiState.update { it.copy(phone = phone, error = null) }
    fun onCompanyChange(company: String) = _uiState.update { it.copy(companyName = company, error = null) }
    fun onPasswordChange(pass: String) = _uiState.update { it.copy(password = pass, error = null) }
    fun onConfirmPasswordChange(pass: String) = _uiState.update { it.copy(confirmPassword = pass, error = null) }

    fun register() {
        val state = _uiState.value

        if (state.name.isBlank() || state.email.isBlank() || state.password.isBlank() || state.companyName.isBlank()) {
            _uiState.update { it.copy(error = "Campos obrigatórios (*) não podem estar vazios.") }
            return
        }

        if (state.password != state.confirmPassword) {
            _uiState.update { it.copy(error = "As senhas não coincidem.") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            repo.register(state.email, state.password)
                .onSuccess {
                    _uiState.update { it.copy(isLoading = false, successMessage = "Usuário cadastrado com sucesso!") }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(isLoading = false, error = e.message) }
                }
        }
    }
}