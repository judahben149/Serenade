package com.judahben149.serenade.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.judahben149.serenade.ui.screens.serenadeHomeScreen.SerenadeHomeScreenViewModel

@Composable
fun HomeScreen(viewModel: SerenadeHomeScreenViewModel) {
    Box(
        modifier = Modifier
            .background(color = Color.Black)
            .fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
//    HomeScreen()
}
