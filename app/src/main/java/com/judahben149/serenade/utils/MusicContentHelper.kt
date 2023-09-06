package com.judahben149.serenade.utils

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.os.ParcelFileDescriptor
import android.provider.MediaStore
import android.util.Log
import android.util.Size
import androidx.annotation.WorkerThread
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


//    fun getAlbumArtThumbnail(uri: Uri): Bitmap? {
//        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            context.contentResolver.loadThumbnail(uri, Size(48, 48), null)
//        } else {
//            try {
//                val parcelFileDescriptor: ParcelFileDescriptor? =
//                    context.contentResolver.openFileDescriptor(uri, "r")
//                val fileDescriptor = parcelFileDescriptor?.fileDescriptor
//
//                val image: Bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
//                parcelFileDescriptor?.close()
//                image
//            } catch (e: Exception) {
//                e.printStackTrace()
//                null
//            }
//        }
//    }

    fun getTrackDetails(contentUriString: String): TrackContent? {
        val contentUri = Uri.parse(contentUriString)

        val contentResolver = context.contentResolver
        var trackContent: TrackContent? = null

        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.SIZE,
            MediaStore.Audio.Media.ARTIST
        )

        // Not used - will use later
        val selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0"
        val sortOrder = "${MediaStore.Audio.Media.DISPLAY_NAME} ASC"

        contentResolver
            .query(contentUri, projection, null, null, null)
            ?.use { cursor ->

                if (cursor.moveToFirst()) {
                    val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID))
                    val displayName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME))
                    val duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION))
                    val size = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE))
                    val artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))

                    trackContent = TrackContent(
                        id = id,
                        name = displayName,
                        duration = duration,
                        size = size,
                        artist = artist,
                        contentUri = contentUri
                    )
                }
            }

        return trackContent
    }

    @WorkerThread
    fun getAlbumArt(context: Context, uri: Uri): Bitmap?{
        val mmr = MediaMetadataRetriever()
        mmr.setDataSource(context, uri)
        val bitmap: Bitmap? = try{
            val data = mmr.embeddedPicture
            if (data != null){
                BitmapFactory.decodeByteArray(data, 0, data.size)
            } else{
                null
            }
        } catch (exp: Exception){
            null
        } finally {
            mmr.release()
        }
        return bitmap
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