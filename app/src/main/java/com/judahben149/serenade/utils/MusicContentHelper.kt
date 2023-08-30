package com.judahben149.serenade.utils

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.ParcelFileDescriptor
import android.provider.MediaStore
import android.util.Log
import android.util.Size
import javax.inject.Inject

class MusicContentHelper @Inject constructor(private val context: Context) {

    fun retrieveAllTracks(): ArrayList<TrackContent> {
        val trackContentList: ArrayList<TrackContent> = ArrayList()

        var query: Cursor?

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

            val audioUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

            val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.SIZE,
                MediaStore.Audio.Media.ARTIST
            )

            query = context.contentResolver.query(
                audioUri,
                projection,
                null,
                null,
                null
            )

        } else {

            val audioUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

            val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.SIZE,
                MediaStore.Audio.Media.ARTIST
            )

            query = context.contentResolver.query(
                audioUri,
                projection,
                null,
                null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER
            )
        }


        query?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val displayNameColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)
            val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)
            val artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)


            if (cursor.count == 0) {
                Log.d("TAG", "retrieveAllTracks: Track List is EMPTY")
            }

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(displayNameColumn)
                val duration = cursor.getInt(durationColumn)
                val size = cursor.getInt(sizeColumn)
                val artist = cursor.getString(artistColumn)

                val contentUri: Uri =
                    ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)

                val trackContent = TrackContent(
                    id = id,
                    name = name ?: "Empty name",
                    duration = duration,
                    size = size,
                    artist = artist ?: "Empty Artist",
                    contentUri = contentUri
                )

                trackContentList.add(trackContent)
            }
        }
        return trackContentList
    }


    fun getAlbumArtThumbnail(uri: Uri): Bitmap? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            context.contentResolver.loadThumbnail(uri, Size(48, 48), null)
        } else {
            try {
                val parcelFileDescriptor: ParcelFileDescriptor? =
                    context.contentResolver.openFileDescriptor(uri, "r")
                val fileDescriptor = parcelFileDescriptor?.fileDescriptor

                val image: Bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
                parcelFileDescriptor?.close()
                image
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}


data class TrackContent(
    val id: Long,
    val name: String?,
    val duration: Int,
    val size: Int,
    val artist: String?,
    val contentUri: Uri,
)