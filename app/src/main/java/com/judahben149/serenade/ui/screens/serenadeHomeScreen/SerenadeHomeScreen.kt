package com.judahben149.serenade.ui.screens.serenadeHomeScreen

import androidx.compose.animation.Animatable
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.judahben149.serenade.R
import com.judahben149.serenade.ui.components.TrackListItemComponent
import com.judahben149.serenade.ui.screens.bottomPlayerCard.BottomCardPlayer
import com.judahben149.serenade.ui.screens.TopAppBarComponent
import com.judahben149.serenade.ui.screens.trackDetail.TrackDetailPlayerSheet
import com.judahben149.serenade.ui.theme.NavButtonDefaultColor
import com.judahben149.serenade.utils.resourceUtils.themeColorSwitcher

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SerenadeHomeScreen(viewModel: SerenadeHomeScreenViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()
    val tracks = state.trackList

    val backgroundOverlayColor =
        state.uiComponentsState.colorPalette?.darkVibrantSwatch?.rgb?.let { Color(it) }
            ?: MaterialTheme.colorScheme.background
    val animatedColor = remember { Animatable(backgroundOverlayColor) }

    val navButtonColor = animateColorAsState(
        targetValue = state.uiComponentsState
            .colorPalette
            ?.darkMutedSwatch
            ?.rgb?.let { Color(it) }
            ?: NavButtonDefaultColor,
        animationSpec = tween(durationMillis = 300, easing = LinearEasing),
        label = "Main button background color"
    )


    val bottomSheetState = rememberModalBottomSheetState()
    val bottomSheetScaffoldState =
        rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContainerColor = state.uiComponentsState.colorPalette?.darkMutedSwatch?.rgb?.let {
            Color(it)
        } ?: Color.Black,
        modifier = Modifier.fillMaxSize(),
        sheetContent = {
            TrackDetailPlayerSheet(state = state, viewModel = viewModel)
        },
        topBar = {
            TopAppBarComponent(viewModel = viewModel, state = state)
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = animatedColor.value
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                if (state.isPerformingCaching) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }

                LazyColumn(
                    modifier = Modifier
                        .weight(1F),
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp)
                ) {

                    items(tracks.size) {
                        TrackListItemComponent(
                            state = state,
                            track = tracks[it],
                            isCurrent = tracks[it].id == state.currentTrack?.id
                        ) { trackId ->
                            viewModel.playTrack(trackId)
                        }
                    }
                }

                if (state.isTrackLoaded) {
                    BottomCardPlayer(
                        state = state,
                        viewModel = viewModel,
                        sheetState = bottomSheetState
                    )
                }


                // Bottom Nav Bar
                NavigationBar(
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = Color.Black
                ) {
                    NavigationBarItem(
                        selected = true,
                        onClick = { /*TODO*/ },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = navButtonColor.value,
                        ),
                        icon = {
                            Icon(
                                modifier = Modifier.size(16.dp),
                                painter = painterResource(id = R.drawable.ic_home_outlined),
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                text = "Home",
                                fontSize = 12.sp,
                                color = themeColorSwitcher(
                                    lightThemeColor = Color.Black,
                                    darkThemeColor = Color.LightGray
                                )
                            )
                        }
                    )

                    NavigationBarItem(
                        selected = false,
                        onClick = {

                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = navButtonColor.value
                        ),
                        icon = {
                            Icon(
                                modifier = Modifier.size(16.dp),
                                painter = painterResource(id = R.drawable.ic_playlist_outlined),
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                text = "Library",
                                fontSize = 12.sp,
                                color = themeColorSwitcher(
                                    lightThemeColor = Color.Black,
                                    darkThemeColor = Color.LightGray
                                )
                            )
                        }
                    )

                    NavigationBarItem(
                        selected = false,
                        onClick = { /*TODO*/ },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = navButtonColor.value
                        ),
                        icon = {
                            Icon(
                                modifier = Modifier.size(16.dp),
                                painter = painterResource(id = R.drawable.ic_disk_outlined),
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                text = "Local",
                                fontSize = 12.sp,
                                color = themeColorSwitcher(
                                    lightThemeColor = Color.Black,
                                    darkThemeColor = Color.LightGray
                                )
                            )
                        }
                    )

                    NavigationBarItem(
                        selected = false,
                        onClick = { /*TODO*/ },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = navButtonColor.value
                        ),
                        icon = {
                            Icon(
                                modifier = Modifier.size(16.dp),
                                painter = painterResource(id = R.drawable.ic_settings_outlined),
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                text = "Settings",
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
    }
}

@Preview(showBackground = true)
@Composable
fun SerenadeHomeScreenPreview() {
    SerenadeHomeScreen()
}
