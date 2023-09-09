package com.judahben149.serenade.domain.usecase

import com.judahben149.serenade.domain.models.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QueueManagementUseCase @Inject constructor() {

    suspend fun prepareNowPlayingQueue(currentTrackId: Long, allTracks: List<Track>): List<Track> {
        return withContext(Dispatchers.IO) {

            val trackList = allTracks.toMutableList()
            val currentTrack = trackList.first { it.trackMediaId == currentTrackId }
            val currentTrackIndex = trackList.indexOf(currentTrack)

            val nowPlayingQueue = arrayListOf<Track>()

            for (i in trackList.indices) {
                if (i > currentTrackIndex) {
                    nowPlayingQueue.add(trackList[i])
                }
            }
            nowPlayingQueue
        }
    }
}