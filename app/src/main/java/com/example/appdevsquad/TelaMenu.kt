package com.example.appdevsquad

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

// Sealed class for menu navigation
sealed class MenuScreen(val route: String, val icon: ImageVector, val title: String) {
    object Painel : MenuScreen("painel", Icons.Filled.Dashboard, "Painel")
    object PDV : MenuScreen("pdv", Icons.Filled.PointOfSale, "PDV")
    object Produtos : MenuScreen("produtos", Icons.Filled.Inventory, "Produtos")
    object Categorias : MenuScreen("categorias", Icons.Filled.Category, "Categorias")
    object Clientes : MenuScreen("clientes", Icons.Filled.Group, "Clientes")
    object Vendas : MenuScreen("vendas", Icons.Filled.TrendingUp, "Vendas")
    object Caixa : MenuScreen("caixa", Icons.Filled.Savings, "Caixa")
    object Financeiro : MenuScreen("financeiro", Icons.Filled.AttachMoney, "Financeiro")
    object Ferias : MenuScreen("ferias", Icons.Filled.WbSunny, "Férias")
}

@Composable
fun TelaMenu(onLogout: () -> Unit) {
    val menuNavController = rememberNavController()
    val menuItems = listOf(
        MenuScreen.Painel,
        MenuScreen.PDV,
        MenuScreen.Produtos,
        MenuScreen.Categorias,
        MenuScreen.Clientes,
        MenuScreen.Vendas,
        MenuScreen.Caixa,
        MenuScreen.Financeiro,
        MenuScreen.Ferias
    )

    val navBackStackEntry by menuNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Row(modifier = Modifier.fillMaxSize()) {
        // Side Navigation Bar
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(250.dp)
                .background(Color(0xFF00A980))
                .padding(16.dp)
        ) {
            // Header
            Text("Próximo fluxo PDV", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Sistema de Gerenciamento", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)

            Spacer(Modifier.height(32.dp))

            // Navigation Items in a scrollable column
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(menuItems) { screen ->
                    NavigationItem(
                        screen = screen,
                        isSelected = currentRoute == screen.route,
                        onClick = {
                            menuNavController.navigate(screen.route) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }

            // Logout Button
            Spacer(Modifier.height(16.dp))
            TextButton(onClick = onLogout, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Filled.ExitToApp, contentDescription = "Sair", tint = Color.White)
                Spacer(Modifier.width(8.dp))
                Text("Sair", color = Color.White, fontSize = 16.sp)
            }
        }

        // Main Content Area
        Box(modifier = Modifier.weight(1f).background(MaterialTheme.colorScheme.background)) {
            NavHost(navController = menuNavController, startDestination = MenuScreen.Painel.route) {
                composable(MenuScreen.Painel.route) { PainelScreen() }
                composable(MenuScreen.PDV.route) { PDVScreen() }
                composable(MenuScreen.Produtos.route) { ProdutosScreen() }
                composable(MenuScreen.Categorias.route) { CategoriasScreen() }
                composable(MenuScreen.Clientes.route) { ClientesScreen() }
                composable(MenuScreen.Vendas.route) { VendasScreen() }
                composable(MenuScreen.Caixa.route) { CaixaScreen() }
                composable(MenuScreen.Financeiro.route) { FinanceiroScreen() }
                composable(MenuScreen.Ferias.route) { FeriasScreen() }
            }
        }
    }
}

@Composable
private fun NavigationItem(screen: MenuScreen, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) Color.White.copy(alpha = 0.1f) else Color.Transparent
    val contentColor = Color.White

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(backgroundColor, shape = RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(screen.icon, contentDescription = screen.title, tint = contentColor)
        Spacer(Modifier.width(16.dp))
        Text(screen.title, color = contentColor, fontWeight = FontWeight.Medium)
    }
}