package com.judahben149.serenade.ui.navigation

sealed class Screens(val route: String) {
    object Home: Screens("home")
    object NowPlaying: Screens("now_playing")
}
