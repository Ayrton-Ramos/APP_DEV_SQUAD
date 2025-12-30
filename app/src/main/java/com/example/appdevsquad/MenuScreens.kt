package com.example.appdevsquad

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

// Placeholder screens for each menu item

@Composable
fun PainelScreen() {
    ScreenContent(name = "Painel")
}

@Composable
fun PDVScreen() {
    ScreenContent(name = "PDV")
}

@Composable
fun ProdutosScreen() {
    ScreenContent(name = "Produtos")
}

@Composable
fun CategoriasScreen() {
    ScreenContent(name = "Categorias")
}

@Composable
fun ClientesScreen() {
    ScreenContent(name = "Clientes")
}

@Composable
fun VendasScreen() {
    ScreenContent(name = "Vendas")
}

@Composable
fun CaixaScreen() {
    ScreenContent(name = "Caixa")
}

@Composable
fun FinanceiroScreen() {
    ScreenContent(name = "Financeiro")
}

@Composable
fun FeriasScreen() {
    ScreenContent(name = "Férias")
}

@Composable
private fun ScreenContent(name: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Tela de $name",
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}