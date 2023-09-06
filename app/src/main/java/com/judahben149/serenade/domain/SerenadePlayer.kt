package com.judahben149.serenade.domain

import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.common.Player.COMMAND_RELEASE
import com.judahben149.serenade.domain.models.Track
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SerenadePlayer @Inject constructor(
    private val player: Player,
) : Player.Listener {

    private var isPlaying: Boolean = false

    private val _playerEvent = MutableSharedFlow<PlayerEvent>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val playerEvent = _playerEvent.asSharedFlow()

    fun performPlayNewTrackAction(track: Track) {
        val metaData = constructMediaMetaDataFromTrack(track)

        val mediaItem = MediaItem.Builder()
            .setUri(Uri.parse(track.contentUri))
            .setMediaId(track.id.toString())
            .setMediaMetadata(metaData)
            .build()

        player.apply {
            setMediaItem(mediaItem)
            prepare()
            play()
        }

        isPlaying = true
        attachPlayProgressListener()
    }

    fun performPlayPauseAction(isPlaying: Boolean) {

        if (isPlaying) {
            player.pause()
            this.isPlaying = false
        } else {
            player.play()
            this.isPlaying = true
            attachPlayProgressListener()
        }
    }

    private fun constructMediaMetaDataFromTrack(track: Track): MediaMetadata {
        return MediaMetadata.Builder()
            .setTitle(track.trackName)
            .setArtist(track.artistName)
            .setDisplayTitle(track.trackName)
            .build()
    }

    private fun attachPlayProgressListener() {
        CoroutineScope(Dispatchers.IO).launch {
            do {
                withContext(Dispatchers.Main) {
                    _playerEvent.tryEmit(PlayerEvent.CurrentPosition(player.currentPosition.toInt()))
                }
                delay(1000)
            } while (isPlaying)
        }
    }

    fun prepareQueue(trackList: List<Track>) {

        CoroutineScope(Dispatchers.IO).launch {
            for (track in trackList) {
                val trackMetadata = constructMediaMetaDataFromTrack(track)
                val mediaItem = MediaItem.Builder().apply {
                    setUri(track.contentUri)
                    setMediaId(track.id.toString())
                    setMediaMetadata(trackMetadata)
                }.build()

                withContext(Dispatchers.Main) {
                    player.addMediaItem(mediaItem)
                }
            }

            withContext(Dispatchers.Main) {
                player.prepare()
            }
        }
    }

    fun playPreviousTrack() {
        player.seekToPreviousMediaItem()
        onTrackChanged()
    }

    fun playNextTrack() {
        player.seekToNextMediaItem()
        onTrackChanged()
    }

    private fun onTrackChanged() {
        _playerEvent.tryEmit(
            PlayerEvent.TrackChanged(
                player.currentMediaItem?.localConfiguration?.uri.toString()
            )
        )
    }

    fun performReleaseActions() {
        if (player.isCommandAvailable(COMMAND_RELEASE)) {
            player.release()
        }
    }

    fun seekTo(playProgress: Int) {
        player.seekTo(playProgress.toLong())
    }
}

sealed class PlayerEvent {
    data class CurrentPosition(val position: Int) : PlayerEvent()
    data class TrackChanged(val currentTrackUri: String) : PlayerEvent()
}