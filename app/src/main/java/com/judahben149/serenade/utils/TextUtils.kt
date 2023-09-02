package com.judahben149.serenade.utils

import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


fun Int.toDurationHHMMSS(): String {
    val duration = (this/1000).toInt()

    val hours = duration / 3600
    val minutes = (duration % 3600) / 60
    val seconds = duration % 60

    val formattedHours = if (hours < 10) "0$hours" else "$hours"
    val formattedMinutes = if (minutes < 10) "0$minutes" else "$minutes"
    val formattedSeconds = if (seconds < 10) "0$seconds" else "$seconds"

    return if (hours > 0) {
        "$formattedHours:$formattedMinutes:$formattedSeconds"
    } else {
        "$formattedMinutes:$formattedSeconds"
    }
}

fun String.encodeUri(): String {
    return URLEncoder.encode(this, StandardCharsets.UTF_8.toString())
}

fun String.decodeUri(): String {
    return URLDecoder.decode(this, StandardCharsets.UTF_8.toString())
}
