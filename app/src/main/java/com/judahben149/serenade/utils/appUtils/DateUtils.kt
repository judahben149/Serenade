package com.judahben149.serenade.utils.appUtils

import java.util.Calendar

fun getGreeting(): String {
    val currentTime = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
    }

    return when(currentTime.get(Calendar.HOUR_OF_DAY)) {
        in 0..11 -> "Good morning"
        in 12..16 -> "Good afternoon"
        else -> "Good evening"
    }
}