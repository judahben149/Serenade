package com.judahben149.serenade.domain.usecase

import com.judahben149.serenade.data.repository.TrackRepositoryImpl
import com.judahben149.serenade.domain.mappers.toTrackEntity
import com.judahben149.serenade.domain.models.Track
import javax.inject.Inject

class SaveTracksToDatabaseUseCase @Inject constructor(
    private val repository: TrackRepositoryImpl
) {

    suspend fun saveTracks(track: Track) {
        repository.saveTracks(track.toTrackEntity())
    }
}