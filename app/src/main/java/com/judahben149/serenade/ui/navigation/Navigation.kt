package com.judahben149.serenade.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.judahben149.serenade.ui.screens.home.HomeScreen
import com.judahben149.serenade.ui.screens.trackDetail.TrackDetailScreen

@Composable
fun Navigation(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = Screens.HomeScreen.route) {

        composable(route = Screens.HomeScreen.route) {
            HomeScreen(navController = navHostController)
        }
        
        composable(route = Screens.TrackDetailScreen.route) {
            val trackId = it.arguments?.getString("trackId")

            trackId?.let { id ->
                TrackDetailScreen(navController = navHostController, trackId = id.toLong())
            }
        }

    }
}