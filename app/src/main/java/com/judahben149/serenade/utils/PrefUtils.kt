package com.judahben149.serenade.utils

import android.content.SharedPreferences
import javax.inject.Inject

class PrefUtils @Inject constructor() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    fun saveString(key: String, value: String) = sharedPreferences.edit().putString(key, value).apply()

    fun fetchString(key: String): String = sharedPreferences.getString(key, "") ?: ""

    fun saveInt(key: String, value: Int) = sharedPreferences.edit().putInt(key, value).apply()

    fun fetchInt(key: String): Int = sharedPreferences.getInt(key, 0)

    fun saveLong(key: String, value: Long) = sharedPreferences.edit().putLong(key, value).apply()

    fun fetchLong(key: String): Long = sharedPreferences.getLong(key, 0L)
}