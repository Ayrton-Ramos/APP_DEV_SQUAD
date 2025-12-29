package com.example.appdevsquad

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier

@Composable
fun DashboardScreen() {
  Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Text("Dashboard — bem-vindo!", style = MaterialTheme.typography.titleLarge)
  }
} 