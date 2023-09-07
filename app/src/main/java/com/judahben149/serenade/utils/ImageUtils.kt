package com.judahben149.serenade.utils

import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import androidx.core.net.toUri

fun getAlbumBitmap(context: Context, url: String?): Bitmap? {
    var bitmap: Bitmap? = null
    val retriever = MediaMetadataRetriever()

    try {
        retriever.setDataSource(url) // Set the data source
        val embedPic = retriever.embeddedPicture // Get the byte array data
        bitmap = BitmapFactory.decodeByteArray(embedPic, 0, embedPic?.size ?: 0) // Convert to a bitmap
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        try {
            retriever.release()
        } catch (e2: Exception) {
            e2.printStackTrace()
        }
    }

    return bitmap
}

fun getAlbumArt(context: Context, uriString: String): Bitmap? {

    val mmr = MediaMetadataRetriever()
    val contentResolver = context.contentResolver
    val takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION

    context.grantUriPermission(context.packageName, Uri.parse(uriString), Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
    contentResolver.takePersistableUriPermission(Uri.parse(uriString), takeFlags)
    mmr.setDataSource(context, Uri.parse(uriString))
    val data = mmr.embeddedPicture

    return if (data != null) {
        BitmapFactory.decodeByteArray(data, 0, data.size)
    } else {
        null
    }
}

fun getEmbeddedAlbumArt() {

}

fun Long.toUri(): Uri {
    return ContentUris.withAppendedId("content://media/external/audio/albumart".toUri(), this)
}

fun Long.toUri2(): String {
    return "content://media/external/audio/albumart/${this}"
}

