package com.judahben149.serenade.domain.usecase

import com.judahben149.serenade.data.repository.TrackRepositoryImpl
import com.judahben149.serenade.domain.mappers.toTrack
import com.judahben149.serenade.domain.models.Track
import com.judahben149.serenade.utils.resourceUtils.MusicContentHelper
import com.judahben149.serenade.utils.resourceUtils.TrackContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllTracksUseCase @Inject constructor(
    private val repository: TrackRepositoryImpl,
    private val musicContentHelper: MusicContentHelper,
) {
    fun getAllTracks(): ArrayList<TrackContent> = musicContentHelper.retrieveAllTracks()

    suspend fun getAllTracksFromDatabase(): Flow<List<Track>> {
        return repository.getAllTracks().map { list ->
            list.map {  trackEntity ->
                trackEntity.toTrack()
            }
        }
    }
}