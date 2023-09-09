package com.judahben149.serenade.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.judahben149.serenade.ui.navigation.Navigation
import com.judahben149.serenade.ui.screens.serenadeHomeScreen.SerenadeHomeScreen
import com.judahben149.serenade.ui.theme.SerenadeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SerenadeTheme {
//                val viewModel = viewModel<MainViewModel>()
//
//                val storageActivityResultLauncher = rememberLauncherForActivityResult(
//                    contract = ActivityResultContracts.RequestPermission(),
//                    onResult = { isGranted ->
//                        viewModel.onPermissionResult(
//                            permission = Manifest.permission.READ_EXTERNAL_STORAGE,
//                            isGranted = isGranted
//                        )
//                    }
//                )
//
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    SideEffect {
//                        storageActivityResultLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
//                    }
//
//                    val navController = rememberNavController()
//                    Navigation(navHostController = navController)
//                }
                SerenadeHomeScreen()
            }
        }
    }

    fun askStoragePermission(viewModel: MainViewModel) {

    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SerenadeTheme {

    }
}