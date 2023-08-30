package com.judahben149.serenade.utils


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
