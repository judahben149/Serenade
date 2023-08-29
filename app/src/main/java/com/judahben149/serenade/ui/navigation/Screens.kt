package com.judahben149.serenade.ui.navigation

sealed class Screens(val route: String) {
    object HomeScreen: Screens("home_screen")
    object TrackDetailScreen: Screens("track_detail_screen")
}
