package com.judahben149.serenade.ui.navigation.bottomNavigation


sealed class BottomNavScreens(val route: String) {
    object Home: BottomNavScreens("home_nav")
    object Library: BottomNavScreens("library_nav")
    object Local: BottomNavScreens("local_nav")
    object Settings: BottomNavScreens("settings_nav")
}
