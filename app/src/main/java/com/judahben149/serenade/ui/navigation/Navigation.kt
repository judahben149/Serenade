package com.judahben149.serenade.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.judahben149.serenade.ui.screens.home.HomeScreen
import com.judahben149.serenade.ui.screens.trackDetail.TrackDetailScreen
import com.judahben149.serenade.utils.encodeUri

@Composable
fun Navigation(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = Screens.HomeScreen.route) {

        composable(route = Screens.HomeScreen.route) {
            HomeScreen(
                navigateToTrackDetail = { trackId, trackContentUri ->
                    navHostController.navigate(
                        Screens.TrackDetailScreen.route
                            .replace(
                                oldValue = "{trackId}",
                                newValue = trackId.toString()
                            )
                            .replace(
                                oldValue = "{trackContentUri}",
                                newValue = trackContentUri.encodeUri()
                            )
                    )
                })
        }

        composable(route = Screens.TrackDetailScreen.route) {
            val trackId = it.arguments?.getString("trackId")
            val trackContentUri = it.arguments?.getString("trackContentUri")

            trackContentUri?.let { uri ->
                TrackDetailScreen(
                    trackId = trackId?.toLong() ?: 0L,
                    encodedTrackContentUri = uri,
                    onNavigate = { }
                )
            }
        }

    }
}