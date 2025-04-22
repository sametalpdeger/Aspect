package com.example.aspectchat.screens.lock.presentation.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LockScreenFormViewModel : ViewModel() {
    val _encryptionKeyInput = MutableStateFlow("")

    fun onEncryptionKeyChange(text: String) {
        _encryptionKeyInput.value = text
    }

    fun onContinue() {
        CoroutineScope(Dispatchers.Default).launch {
        }
    }
}