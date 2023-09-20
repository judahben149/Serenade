package com.judahben149.serenade.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.judahben149.serenade.ui.screens.serenadeHomeScreen.SerenadeHome
import com.judahben149.serenade.ui.screens.serenadeHomeScreen.SerenadeHomeScreenViewModel
import com.judahben149.serenade.ui.screens.trackDetail.NowPlayingScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: SerenadeHomeScreenViewModel,
) {

    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {

        composable(route = Screens.Home.route) {
            SerenadeHome(
                viewModel = viewModel,
                onNavigateToNowPlaying = {
                    navController.navigate(Screens.NowPlaying.route)
                }
            )
        }

        composable(route = Screens.NowPlaying.route) {
            NowPlayingScreen(viewModel)
        }
    }
}