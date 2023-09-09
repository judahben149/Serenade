package com.judahben149.serenade.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.judahben149.serenade.ui.components.ChipComponent
import com.judahben149.serenade.ui.screens.serenadeHomeScreen.SerenadeHomeScreenState
import com.judahben149.serenade.ui.screens.serenadeHomeScreen.SerenadeHomeScreenViewModel
import com.judahben149.serenade.ui.theme.Heading_Dark
import com.judahben149.serenade.ui.theme.Heading_Light
import com.judahben149.serenade.utils.resourceUtils.themeColorSwitcher

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComponent(viewModel: SerenadeHomeScreenViewModel, state: SerenadeHomeScreenState) {
    LargeTopAppBar(
        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color.Transparent),
        navigationIcon = {
            Text(
                text = "Serenade",
                color = themeColorSwitcher(
                    lightThemeColor = Heading_Light,
                    darkThemeColor = Heading_Dark
                ),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )
        },
        title = {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ChipComponent(
                    text = "Music",
//                    isSelected = state.trackType == TrackType.MUSIC,
                    onClick = {
//                        viewModel.updateTrackType(TrackType.MUSIC)
                    }
                )
                ChipComponent(
                    text = "Podcasts & Shows",
//                    isSelected = state.trackType == TrackType.PODCASTS,
//                    onClick = { viewModel.updateTrackType(TrackType.PODCASTS) }
                )
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search")
            }
        }
    )
}