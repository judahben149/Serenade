package com.judahben149.serenade.utils.serializationUtils

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.judahben149.serenade.domain.models.SerenadeColor
import com.judahben149.serenade.domain.models.SerenadeSwatch
import java.lang.reflect.Type


class SerenadeSwatchSerializer : JsonSerializer<SerenadeSwatch> {
    override fun serialize(
        src: SerenadeSwatch?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        val jsonObject = JsonObject()
        src?.let {
            jsonObject.addProperty("rgb", it.rgb)
            jsonObject.addProperty("titleTextColor", it.titleTextColor)
            jsonObject.addProperty("bodyTextColor", it.bodyTextColor)
        }
        return jsonObject
    }
}


class SerenadeColorSerializer : JsonSerializer<SerenadeColor> {
    override fun serialize(
        src: SerenadeColor?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        val jsonObject = JsonObject()
        src?.let {
            jsonObject.add("lightVibrantSwatch", context?.serialize(it.lightVibrantSwatch))
            jsonObject.add("vibrantSwatch", context?.serialize(it.vibrantSwatch))
            jsonObject.add("darkVibrantSwatch", context?.serialize(it.darkVibrantSwatch))
            jsonObject.add("lightMutedSwatch", context?.serialize(it.lightMutedSwatch))
            jsonObject.add("mutedSwatch", context?.serialize(it.mutedSwatch))
            jsonObject.add("darkMutedSwatch", context?.serialize(it.darkMutedSwatch))
        }
        return jsonObject
    }
}
