package com.judahben149.serenade.ui.screens.home

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.judahben149.serenade.ui.components.ChipComponent
import com.judahben149.serenade.ui.components.SnackBarComponent

@Composable
fun HomeScreen(navController: NavHostController, homeViewModel: HomeViewModel = hiltViewModel()) {
    val scope = rememberCoroutineScope()
    val state by homeViewModel.state.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackBarComponent(hostState = snackBarHostState) },
        topBar = { TopAppBarComponent(homeViewModel, state) }

    ) { paddingValues ->

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComponent(viewModel: HomeViewModel, state: HomeUIState) {
    LargeTopAppBar(
        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color.Transparent),
        navigationIcon = {
            Text(
                text = "Serenade",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )
        },
        title = {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ChipComponent(
                    text = "Music",
                    isSelected = state.trackType == TrackType.MUSIC,
                    onClick = { viewModel.updateTrackType(TrackType.MUSIC) }
                )
                ChipComponent(
                    text = "Podcasts & Shows",
                    isSelected = state.trackType == TrackType.PODCASTS,
                    onClick = { viewModel.updateTrackType(TrackType.PODCASTS) }
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

@Preview(showBackground = true)
@Composable
fun TopAppBarComponentPreview() {
//    TopAppBarComponent()
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = NavHostController(LocalContext.current))
}
