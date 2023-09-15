package com.judahben149.serenade.utils.appUtils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable

@Composable
fun isAppInDarkMode(): Boolean {
    return isSystemInDarkTheme()
}