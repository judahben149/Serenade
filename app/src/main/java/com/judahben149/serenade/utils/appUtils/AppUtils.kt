package com.judahben149.serenade.utils.appUtils

import android.os.Build
import androidx.annotation.IntRange


fun convertApiLevel(androidVersion: Int): Int {

    return when(androidVersion) {
        5 -> Build.VERSION_CODES.LOLLIPOP
        6 -> Build.VERSION_CODES.M
        7 -> Build.VERSION_CODES.N
        8 -> Build.VERSION_CODES.O
        9 -> Build.VERSION_CODES.P
        10 -> Build.VERSION_CODES.Q
        11 -> Build.VERSION_CODES.R
        12 -> Build.VERSION_CODES.S
        13 -> Build.VERSION_CODES.TIRAMISU
        else -> throw IllegalStateException()
    }
}

fun isApiLevelAbove(@IntRange(5, 14) androidVersion: Int): Boolean {
    val apiLevel = convertApiLevel(androidVersion)

    return Build.VERSION.SDK_INT > apiLevel
}

fun isApiLevelEqualToOrAbove(@IntRange(6, 14) androidVersion: Int): Boolean {
    val apiLevel = convertApiLevel(androidVersion)

    return Build.VERSION.SDK_INT >= apiLevel
}