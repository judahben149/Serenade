package com.judahben149.serenade.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.judahben149.serenade.ui.screens.BaseScreen
import com.judahben149.serenade.ui.screens.serenadeHomeScreen.SerenadeHome
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
//                SerenadeHome()
                BaseScreen()
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