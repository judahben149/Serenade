package com.judahben149.serenade.utils

import timber.log.Timber

fun String.logThis(tag: String = "") {
    Timber.tag(tag).d(this)
}

fun Int.logThis(tag: String = "") {
    Timber.tag(tag).d(this.toString())
}

fun Long.logThis(tag: String = "") {
    Timber.tag(tag).d(this.toString())
}

fun Any.logThis(tag: String = "") {
    Timber.tag(tag).d(this.toString())
}

