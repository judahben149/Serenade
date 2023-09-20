package com.judahben149.serenade.ui.navigation.bottomNavigation

import androidx.annotation.DrawableRes
import com.judahben149.serenade.ui.screens.BottomNavDestinations
import com.judahben149.serenade.R
import com.judahben149.serenade.ui.screens.serenadeHomeScreen.SelectedBottomBarItem

data class BottomNavigationItem(
    val label: String = "",
    val destinationName: SelectedBottomBarItem,
    @DrawableRes val outlinedIconResource: Int,
    @DrawableRes val filledIconResource: Int,
    val route: String,
)

object BottomNavItems {
    val list = listOf(
        BottomNavigationItem(
            label = "Home",
            destinationName = SelectedBottomBarItem.HOME,
            outlinedIconResource = R.drawable.ic_home_outlined,
            filledIconResource = R.drawable.ic_home_filled,
            route = BottomNavRoutes.Home.route
        ),
        BottomNavigationItem(
            label = "Library",
            destinationName = SelectedBottomBarItem.LIBRARY,
            outlinedIconResource = R.drawable.ic_playlist_outlined,
            filledIconResource = R.drawable.ic_playlist_filled,
            route = BottomNavRoutes.Library.route
        ),
        BottomNavigationItem(
            label = "Local",
            destinationName = SelectedBottomBarItem.LOCAL,
            outlinedIconResource = R.drawable.ic_disk_outlined,
            filledIconResource = R.drawable.ic_disk_filled,
            route = BottomNavRoutes.Local.route
        ),
        BottomNavigationItem(
            label = "Settings",
            destinationName = SelectedBottomBarItem.SETTINGS,
            outlinedIconResource = R.drawable.ic_settings_outlined,
            filledIconResource = R.drawable.ic_settings_filled,
            route = BottomNavRoutes.Settings.route
        ),
    )
}

enum class BottomNavigationItems(
    val label: String = "",
    val destinationName: BottomNavDestinations,
    @DrawableRes val outlinedIconResource: Int,
    @DrawableRes val filledIconResource: Int,
    val route: String,
) {
    Home(
        "Home",
        BottomNavDestinations.HOME,
        R.drawable.ic_home_outlined,
        R.drawable.ic_home_filled,
        BottomNavRoutes.Home.route
    ),

    Library(
        "Library",
        BottomNavDestinations.LIBRARY,
        R.drawable.ic_playlist_outlined,
        R.drawable.ic_playlist_filled,
        BottomNavRoutes.Library.route
    ),

    Local(
        "Local",
        BottomNavDestinations.LOCAL,
        R.drawable.ic_disk_outlined,
        R.drawable.ic_disk_filled,
        BottomNavRoutes.Local.route
    ),

    Settings(
        "Settings",
        BottomNavDestinations.SETTINGS,
        R.drawable.ic_settings_outlined,
        R.drawable.ic_settings_filled,
        BottomNavRoutes.Settings.route
    ),
}
