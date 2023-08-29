package com.judahben149.serenade.ui.components

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable

@Composable
fun SnackBarComponent(hostState: SnackbarHostState) {
    
    SnackbarHost(hostState = hostState) {
        Snackbar(snackbarData = it)
    }
}