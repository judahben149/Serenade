package com.judahben149.serenade.ui.screens

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.judahben149.serenade.ui.navigation.bottomNavigation.BottomNavigationItem
import com.judahben149.serenade.ui.screens.serenadeHomeScreen.SelectedBottomBarItem
import com.judahben149.serenade.ui.screens.serenadeHomeScreen.HomeUIState
import com.judahben149.serenade.ui.theme.NavButtonDefaultColor
import com.judahben149.serenade.ui.theme.NavContainerDefaultColor
import com.judahben149.serenade.utils.resourceUtils.themeColorSwitcher

@Composable
fun BottomNavBarComponent(
    state: HomeUIState,
    currentItem: SelectedBottomBarItem,
    bottomNavItems: List<BottomNavigationItem>,
    onNavItemClick: (route: String) -> Unit,
) {

    val navContainerColor = animateColorAsState(
        targetValue = themeColorSwitcher(
            lightThemeColor = state.uiComponentsState
                .colorPalette
                ?.darkMutedSwatch
                ?.rgb?.let { Color(it) }?.copy(alpha = 0.2F)
                ?: NavContainerDefaultColor,
            darkThemeColor = Color.Black
        ),
        animationSpec = tween(durationMillis = 300, easing = LinearEasing),
        label = "Main button background color"
    )

    val navButtonColor = animateColorAsState(
        targetValue = state.uiComponentsState
            .colorPalette
            ?.darkMutedSwatch
            ?.rgb?.let { Color(it) }?.copy(0.2f)
            ?: NavButtonDefaultColor.copy(0.1f),
        animationSpec = tween(durationMillis = 300, easing = LinearEasing),
        label = "Main button background color"
    )

// Bottom Nav Bar
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = navContainerColor.value
    ) {

        for (item in bottomNavItems) {
            val isSelected = item.destinationName == currentItem

            NavigationBarItem(
                selected = isSelected,
                onClick = { onNavItemClick(item.route) },
                icon = {
                    Crossfade(
                        targetState = isSelected,
                        label = "home_bottom_bar"
                    ) { itemIsSelected ->
                        Icon(
                            modifier = Modifier.size(16.dp),
                            painter = if (itemIsSelected) {
                                painterResource(id = item.filledIconResource)
                            } else {
                                painterResource(id = item.outlinedIconResource)
                            },
                            contentDescription = null
                        )
                    }
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 12.sp,
                        color = themeColorSwitcher(
                            lightThemeColor = Color.Black,
                            darkThemeColor = Color.LightGray
                        )
                    )
                }
            )
        }
    }
}


enum class BottomNavDestinations {
    HOME,
    LIBRARY,
    LOCAL,
    SETTINGS
}

