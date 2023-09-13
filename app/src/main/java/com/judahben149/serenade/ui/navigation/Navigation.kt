package com.judahben149.serenade.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.judahben149.serenade.ui.navigation.bottomNavigation.BottomNavScreens
import com.judahben149.serenade.ui.screens.HomeScreen
import com.judahben149.serenade.ui.screens.LibraryScreen
import com.judahben149.serenade.ui.screens.LocalScreen
import com.judahben149.serenade.ui.screens.SettingsScreen

@Composable
fun Navigation(
    navHostController: NavHostController,
    modifier: Modifier
) {

    NavHost(
        navController = navHostController,
        startDestination = BottomNavScreens.Home.route
    ) {

        composable(route = BottomNavScreens.Home.route) {
            HomeScreen()
        }

        composable(route = BottomNavScreens.Library.route) {
            LibraryScreen()
        }

        composable(route = BottomNavScreens.Local.route) {
            LocalScreen()
        }

        composable(route = BottomNavScreens.Settings.route) {
            SettingsScreen()
        }
    }
}