package com.judahben149.serenade.ui.navigation.bottomNavigation


sealed class BottomNavRoutes(val route: String) {
    object Home: BottomNavRoutes("home_nav")
    object Library: BottomNavRoutes("library_nav")
    object Local: BottomNavRoutes("local_nav")
    object Settings: BottomNavRoutes("settings_nav")
}
