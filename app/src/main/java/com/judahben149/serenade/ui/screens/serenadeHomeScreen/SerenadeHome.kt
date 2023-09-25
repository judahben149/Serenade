package com.judahben149.serenade.ui.screens.serenadeHomeScreen

import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.judahben149.serenade.ui.navigation.bottomNavigation.BottomNavItems
import com.judahben149.serenade.ui.navigation.bottomNavigation.BottomNavRoutes
import com.judahben149.serenade.ui.screens.BottomNavBarComponent
import com.judahben149.serenade.ui.screens.HomeScreen
import com.judahben149.serenade.ui.screens.LibraryScreen
import com.judahben149.serenade.ui.screens.LocalScreen
import com.judahben149.serenade.ui.screens.SerenadeTopAppBar
import com.judahben149.serenade.ui.screens.SettingsScreen
import com.judahben149.serenade.ui.screens.bottomPlayerCard.BottomCardPlayer

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun SerenadeHome(
    viewModel: SerenadeHomeScreenViewModel,
    onNavigateToNowPlaying:() -> Unit
) {

    val state by viewModel.state.collectAsState()

    val backgroundOverlayColor =
        state.uiComponentsState.colorPalette?.darkVibrantSwatch?.rgb?.let { Color(it) }
            ?: MaterialTheme.colorScheme.background
    val animatedColor = remember { Animatable(backgroundOverlayColor) }


    val bottomSheetState = rememberModalBottomSheetState()
    val bottomSheetScaffoldState =
        rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { SerenadeTopAppBar(viewModel = viewModel, state = state) },
        bottomBar = { HomeBottomBar(viewModel) },
        content = { contentPadding ->


            Column(
                modifier = Modifier
                    .padding(contentPadding)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                if (state.isPerformingCaching) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth().weight(1f, false))
                }

                AnimatedContent(
                    label = "home_content",
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f, fill = true),
                    targetState = state.bottomBarState.selected
                ) { selectedItem ->

                    when (selectedItem) {
                        SelectedBottomBarItem.HOME -> {
                            HomeScreen(viewModel)
                        }

                        SelectedBottomBarItem.LIBRARY -> {
                            LibraryScreen()
                        }

                        SelectedBottomBarItem.LOCAL -> {
                            LocalScreen(viewModel)
                        }
                    }
                }

                if (state.isTrackLoaded) {
                    BottomCardPlayer(
                        state = state,
                        viewModel = viewModel,
                        modifier = Modifier.weight(1f, false),
                        sheetState = bottomSheetState,
                        onCardClick = {
                            onNavigateToNowPlaying()
                        }
                    )
                }
            }
        }
    )
}


@Composable
fun HomeBottomBar(viewModel: SerenadeHomeScreenViewModel) {
    val state by viewModel.state.collectAsState()

    BottomNavBarComponent(
        state = state,
        currentItem = state.bottomBarState.selected,
        bottomNavItems = BottomNavItems.list
    ) { bottomNavRoute ->
        when (bottomNavRoute) {

            BottomNavRoutes.Home.route -> {
                viewModel.updateSelectedBottomBarItem(SelectedBottomBarItem.HOME)
            }

            BottomNavRoutes.Library.route -> {
                viewModel.updateSelectedBottomBarItem(SelectedBottomBarItem.LIBRARY)
            }

            BottomNavRoutes.Local.route -> {
                viewModel.updateSelectedBottomBarItem(SelectedBottomBarItem.LOCAL)
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun SerenadeHomeScreenPreview() {
//    SerenadeHome()
}
