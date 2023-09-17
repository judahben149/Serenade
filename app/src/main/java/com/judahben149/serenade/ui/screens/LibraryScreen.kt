package com.judahben149.serenade.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LibraryScreen() {
    Box(modifier = Modifier.background(color = Color.Red))
}

@Preview(showBackground = true)
@Composable
fun LibraryScreenPreview() {
    LibraryScreen()
}
