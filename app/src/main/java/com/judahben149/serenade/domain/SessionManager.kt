package com.judahben149.serenade.domain

import com.judahben149.serenade.utils.Constants.ARE_TRACKS_CACHED
import com.judahben149.serenade.utils.appUtils.PrefUtils
import javax.inject.Inject

class SessionManager @Inject constructor(
    private val prefUtils: PrefUtils
) {

    fun getIsCachingComplete(): Boolean {
        return prefUtils.fetchBoolean(ARE_TRACKS_CACHED, false)
    }

    fun saveCachingComplete() {
        prefUtils.saveBoolean(ARE_TRACKS_CACHED, true)
    }
}