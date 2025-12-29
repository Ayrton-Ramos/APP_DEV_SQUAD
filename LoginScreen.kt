package com.example.appdevsquad

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun LoginScreen(
  uiState: LoginUiState,
  onEmail: (String) -> Unit,
  onPassword: (String) -> Unit,
  onLogin: () -> Unit,
  onSuccess: () -> Unit
) {
  // observar sucesso e chamar callback de navegação
  LaunchedEffect(uiState.success) {
    if (uiState.success) onSuccess()
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(24.dp),
    verticalArrangement = Arrangement.Center
  ) {
    Text(text = "Entrar", style = MaterialTheme.typography.headlineMedium)
    Spacer(Modifier.height(16.dp))

    OutlinedTextField(
      value = uiState.email,
      onValueChange = onEmail,
      label = { Text("E-mail / usuário") },
      singleLine = true,
      modifier = Modifier.fillMaxWidth()
    )

    Spacer(Modifier.height(8.dp))

    OutlinedTextField(
      value = uiState.password,
      onValueChange = onPassword,
      label = { Text("Senha") },
      singleLine = true,
      visualTransformation = PasswordVisualTransformation(),
      modifier = Modifier.fillMaxWidth()
    )

    uiState.error?.let {
      Spacer(Modifier.height(8.dp))
      Text(text = it, color = MaterialTheme.colorScheme.error)
    }

    Spacer(Modifier.height(16.dp))

    Button(
      onClick = onLogin,
      enabled = !uiState.isLoading,
      modifier = Modifier.fillMaxWidth()
    ) {
      if (uiState.isLoading) {
        CircularProgressIndicator(modifier = Modifier.size(18.dp), strokeWidth = 2.dp)
        Spacer(Modifier.width(8.dp))
      }
      Text("Entrar")
    }
  }
} 