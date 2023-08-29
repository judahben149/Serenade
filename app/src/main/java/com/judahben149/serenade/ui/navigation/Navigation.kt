package com.judahben149.serenade.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.judahben149.serenade.ui.screens.home.HomeScreen

@Composable
fun Navigation(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = Screens.HomeScreen.route) {

        composable(
            route = Screens.HomeScreen.route
        ) {
            HomeScreen(navController = navHostController)
        }


    }
}