package com.example.appdevsquad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onEmail: (String) -> Unit,
    onPassword: (String) -> Unit,
    onLogin: () -> Unit,
    onSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    LaunchedEffect(uiState.success) {
        if (uiState.success) onSuccess()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        // Icon
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color(0xFF00B2FF), shape = RoundedCornerShape(12.dp))
        )

        Spacer(Modifier.height(32.dp))

        // Titles
        Text(text = "Login", style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold))
        Spacer(Modifier.height(8.dp))
        Text(text = "Cresça seu negócio com confiança.", style = MaterialTheme.typography.bodyLarge, color = Color.Gray)

        Spacer(Modifier.height(32.dp))

        // Form
        Text(text = "Usuário*", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        Spacer(Modifier.height(4.dp))
        TextField(
            value = uiState.email,
            onValueChange = onEmail,
            placeholder = { Text("owner@example.com", color = Color.Gray) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF2A2A2B),
                focusedContainerColor = Color(0xFF2A2A2B),
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )

        Spacer(Modifier.height(16.dp))

        Text(text = "Password*", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        Spacer(Modifier.height(4.dp))
        TextField(
            value = uiState.password,
            onValueChange = onPassword,
            placeholder = { Text("••••••••", color = Color.Gray) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF2A2A2B),
                focusedContainerColor = Color(0xFF2A2A2B),
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )

        uiState.error?.let {
            Spacer(Modifier.height(8.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = onLogin,
            enabled = !uiState.isLoading,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
            } else {
                Text("Login", fontSize = 16.sp)
            }
        }

        Spacer(Modifier.height(24.dp))

        // Register link
        val annotatedString = buildAnnotatedString {
            append("Ainda não tem acesso? ")
            pushStringAnnotation(tag = "register", annotation = "register")
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)) {
                append("Criar conta")
            }
            pop()
        }

        ClickableText(
            text = annotatedString,
            onClick = { offset ->
                annotatedString.getStringAnnotations(tag = "register", start = offset, end = offset)
                    .firstOrNull()?.let {
                        onNavigateToRegister()
                    }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}