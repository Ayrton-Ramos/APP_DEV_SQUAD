package com.example.appdevsquad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
fun RegisterScreen(onNavigateToLogin: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp)
            .padding(top = 48.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Icon
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color(0xFF00A980), shape = RoundedCornerShape(12.dp))
        )

        Spacer(Modifier.height(24.dp))

        // Titles
        Text(text = "Criar Conta", style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold))
        Spacer(Modifier.height(8.dp))
        Text(text = "Comece a gerenciar seu negócio agora mesmo", style = MaterialTheme.typography.bodyLarge, color = Color.Gray)

        Spacer(Modifier.height(32.dp))

        // Form
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Nome Completo*") },
            placeholder = { Text("Digite seu nome completo", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null, tint = Color.Gray) },
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

        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Email*") },
            placeholder = { Text("seu@email.com", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null, tint = Color.Gray) },
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

        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Telefone") },
            placeholder = { Text("(00) 00000-0000", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Filled.Phone, contentDescription = null, tint = Color.Gray) },
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

        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Nome da Empresa*") },
            placeholder = { Text("owner@example.com", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Filled.Business, contentDescription = null, tint = Color.Gray) },
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

        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Senha*") },
            placeholder = { Text("••••••••", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null, tint = Color.Gray) },
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

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Criar Conta", fontSize = 16.sp)
        }

        Spacer(Modifier.height(24.dp))

        // Login link
        val annotatedString = buildAnnotatedString {
            append("Já tem uma conta? ")
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
                    .firstOrNull()?.let {
                        onNavigateToLogin()
                    }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}