package com.judahben149.serenade.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    val permissionDialogQueue = mutableStateListOf<String>()

    fun dismissDialog() {
        permissionDialogQueue.removeLast()
    }

    fun onPermissionResult(permission: String, isGranted: Boolean) {
        if (!isGranted) {
            permissionDialogQueue.add(0, permission)
        }
    }
 }