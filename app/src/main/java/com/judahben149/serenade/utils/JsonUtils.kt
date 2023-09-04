package com.judahben149.serenade.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.judahben149.serenade.domain.models.Track

fun List<Track>.serializeTrackListToJson(): String {
    return Gson().toJson(this)
}

fun String.deserializeTrackListFromJson(): List<Track> {
    val type = object : TypeToken<List<Track>>() {}.type

    return Gson().fromJson(this, type)
}