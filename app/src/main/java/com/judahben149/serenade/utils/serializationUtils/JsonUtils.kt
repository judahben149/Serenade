package com.judahben149.serenade.utils.serializationUtils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.judahben149.serenade.domain.models.SerenadeColor
import com.judahben149.serenade.domain.models.Track
import javax.inject.Inject
import javax.inject.Named

fun List<Track>.serializeTrackListToJson(): String {
    return Gson().toJson(this)
}

fun String.deserializeTrackListFromJson(): List<Track> {
    val type = object : TypeToken<List<Track>>() {}.type

    return Gson().fromJson(this, type)
}

class JsonUtils @Inject constructor(@Named("serenade_gson") val gson: Gson) {

    fun SerenadeColor.serializeColorToJson(): String = gson.toJson(this)

    fun String.deserializeColorToJson(): SerenadeColor  = gson.fromJson(this, SerenadeColor::class.java)
}
