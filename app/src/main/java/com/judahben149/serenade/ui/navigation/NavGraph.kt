package com.judahben149.serenade.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.judahben149.serenade.ui.screens.serenadeHomeScreen.SerenadeHome
import com.judahben149.serenade.ui.screens.serenadeHomeScreen.SerenadeHomeScreenViewModel

@Composable
fun NavGraph(
    navHostController: NavHostController,
    viewModel: SerenadeHomeScreenViewModel
) {

    NavHost(
        navController = navHostController,
        startDestination = Screens.Home.route
    ) {

        composable(route = Screens.Home.route) {
            SerenadeHome(viewModel)
        }
    }
}