package com.judahben149.serenade.domain.mappers

import com.judahben149.serenade.data.sources.local.entity.TrackEntity
import com.judahben149.serenade.domain.models.Track
import com.judahben149.serenade.utils.resourceUtils.TrackContent

fun TrackEntity.toTrack(): Track {
    return Track(
        id = id,
        trackMediaId = trackMediaId,
        trackName = trackName,
        artistName = artistName,
        thumbnail = thumbnail,
        duration = duration,
        contentUri = contentUri,
        albumArtUri = albumArtUri
    )
}

fun Track.toTrackEntity(): TrackEntity {
    return TrackEntity(
        id = 0,
        trackMediaId = trackMediaId,
        trackName = trackName,
        artistName = artistName,
        thumbnail = thumbnail,
        duration = duration,
        contentUri = contentUri,
        albumArtUri = albumArtUri
    )
}

fun TrackContent.toTrack(albumArtUri: String): Track {
    return Track(
        trackMediaId = this.id,
        trackName = this.name ?: "No Track Name",
        artistName = this.artist ?: "No Artist Name",
        duration = this.duration,
        contentUri = this.contentUri.toString(),
        albumArtUri = albumArtUri
    )
}