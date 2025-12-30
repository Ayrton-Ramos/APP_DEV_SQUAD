package com.example.appdevsquad

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RegisterScreen(onNavigateToLogin: () -> Unit) {
    val vm: RegisterViewModel = hiltViewModel()
    val uiState by vm.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(uiState.successMessage) {
        uiState.successMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            onNavigateToLogin()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(Modifier.height(48.dp))

        // Icon
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color(0xFF00A980), shape = RoundedCornerShape(12.dp))
        )

        Spacer(Modifier.height(24.dp))

        // Titles
        Text(text = "Criar Conta", style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold))
        Text(text = "Comece a gerenciar seu negócio agora mesmo", style = MaterialTheme.typography.bodyLarge, color = Color.Gray)

        Spacer(Modifier.height(32.dp))

        // Form
        TextField(
            value = uiState.name,
            onValueChange = vm::onNameChange,
            label = { Text("Nome Completo*") },
            placeholder = { Text("Digite seu nome completo", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null, tint = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = uiState.email,
            onValueChange = vm::onEmailChange,
            label = { Text("Email*") },
            placeholder = { Text("seu@email.com", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null, tint = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = uiState.phone,
            onValueChange = vm::onPhoneChange,
            label = { Text("Telefone") },
            placeholder = { Text("(00) 00000-0000", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Filled.Phone, contentDescription = null, tint = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = uiState.companyName,
            onValueChange = vm::onCompanyChange,
            label = { Text("Nome da Empresa*") },
            placeholder = { Text("owner@example.com", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Filled.Business, contentDescription = null, tint = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = uiState.password,
            onValueChange = vm::onPasswordChange,
            label = { Text("Senha*") },
            placeholder = { Text("••••••••", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null, tint = Color.Gray) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = uiState.confirmPassword,
            onValueChange = vm::onConfirmPasswordChange,
            label = { Text("Repita a Senha*") },
            placeholder = { Text("••••••••", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null, tint = Color.Gray) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )

        uiState.error?.let {
            Spacer(Modifier.height(16.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error, modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = vm::register,
            enabled = !uiState.isLoading,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
            } else {
                Text("Criar Conta", fontSize = 16.sp)
            }
        }

        Spacer(Modifier.height(24.dp))

        // Login link
        val annotatedString = buildAnnotatedString {
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                append("Já é cadastrado? ")
            }
            pushStringAnnotation(tag = "login", annotation = "login")
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)) {
                append("Fazer Login")
            }
            pop()
        }

        ClickableText(
            text = annotatedString,
            onClick = { offset ->
                annotatedString.getStringAnnotations(tag = "login", start = offset, end = offset)
                    .firstOrNull()?.let { onNavigateToLogin() }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(48.dp))
    }
}