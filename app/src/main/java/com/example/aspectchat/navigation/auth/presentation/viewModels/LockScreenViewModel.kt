package com.example.aspectchat.navigation.auth.presentation.viewModels

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aspectchat.core.data._nonPersistEncryptionKey
import com.example.aspectchat.core.data.datastore.UserAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LockScreenViewModel @Inject constructor(
    private val userAccountDataStore: DataStore<UserAccount>,
) : ViewModel() {
    val _encryptionKeyInput = MutableStateFlow<String>("")
    val isOpen = _nonPersistEncryptionKey
        .map { it != null }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            null
        )

    fun onEncryptionKeyChange(text: String) {
        _encryptionKeyInput.value = text
    }

    fun onContinue() {
        CoroutineScope(Dispatchers.Default).launch {
        }
    }
}