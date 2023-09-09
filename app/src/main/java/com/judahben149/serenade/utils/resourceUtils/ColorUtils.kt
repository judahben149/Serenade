package com.judahben149.serenade.utils.resourceUtils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.judahben149.serenade.utils.appUtils.isAppInDarkMode

@Composable
fun themeColorSwitcher(lightThemeColor: Color, darkThemeColor: Color): Color {
    return if (isAppInDarkMode()) darkThemeColor else lightThemeColor
}

object DefaultColors {
    const val LIGHT_VIBRANT_RGB = 0xFFAABBCC.toInt()
    const val LIGHT_VIBRANT_TITLE_TEXT_COLOR = 0xFF223344.toInt()
    const val LIGHT_VIBRANT_BODY_TEXT_COLOR = 0xFF556677.toInt()

    const val VIBRANT_RGB = 0xFFDDEEFF.toInt()
    const val VIBRANT_TITLE_TEXT_COLOR = 0xFF445566.toInt()
    const val VIBRANT_BODY_TEXT_COLOR = 0xFF778899.toInt()

    const val DARK_VIBRANT_RGB = 0xFF112233.toInt()
    const val DARK_VIBRANT_TITLE_TEXT_COLOR = 0xFF998877.toInt()
    const val DARK_VIBRANT_BODY_TEXT_COLOR = 0xFF665544.toInt()

    const val LIGHT_MUTED_RGB = 0xFF445566.toInt()
    const val LIGHT_MUTED_TITLE_TEXT_COLOR = 0xFFBBCCDD.toInt()
    const val LIGHT_MUTED_BODY_TEXT_COLOR = 0xFFEEDDCC.toInt()

    const val MUTED_RGB = 0xFF778899.toInt()
    const val MUTED_TITLE_TEXT_COLOR = 0xFF223344.toInt()
    const val MUTED_BODY_TEXT_COLOR = 0xFF556677.toInt()

    const val DARK_MUTED_RGB = 0xFF001122.toInt()
    const val DARK_MUTED_TITLE_TEXT_COLOR = 0xFF998877.toInt()
    const val DARK_MUTED_BODY_TEXT_COLOR = 0xFF665544.toInt()
}
