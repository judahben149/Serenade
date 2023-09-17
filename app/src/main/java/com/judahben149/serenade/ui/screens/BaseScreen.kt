package com.judahben149.serenade.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.judahben149.serenade.ui.navigation.NavGraph
import com.judahben149.serenade.ui.screens.serenadeHomeScreen.SerenadeHomeScreenViewModel
import com.judahben149.serenade.ui.theme.SerenadeTheme

@Composable
fun BaseScreen(viewModel: SerenadeHomeScreenViewModel = hiltViewModel()) {
    SerenadeTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val navController = rememberNavController()

            NavGraph(
                navHostController = navController,
                viewModel = viewModel
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BaseScreenPreview() {
    BaseScreen()
}
