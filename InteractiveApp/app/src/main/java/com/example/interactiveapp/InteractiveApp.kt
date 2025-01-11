package com.example.interactiveapp

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature.capitalizergenerator.CapitalizerGeneratorScreen
import com.example.feature.coreui.MenuScreen
import com.example.feature.passwordgenerator.RandomPasswordGeneratorScreen
import com.example.feature.presidents.list.PresidentListScreen

@Composable
fun InteractiveApp(snackbarHostState: SnackbarHostState) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Menu") {

        composable("Menu") {
            MenuScreen(
                navController = navController,
                snackbarHostState = snackbarHostState
            )
        }

        composable("RandomPasswordGenerator") {
            RandomPasswordGeneratorScreen(
                navController = navController,
                snackbarHostState = snackbarHostState
            )
        }

        composable("CapitalizerGenerator") {
            CapitalizerGeneratorScreen(
                navController = navController,
                snackbarHostState = snackbarHostState
            )
        }

        composable("PresidentList") {
            PresidentListScreen(
                navController = navController,
                snackbarHostState = snackbarHostState
            )
        }
    }
}