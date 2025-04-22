package com.example.aspectchat.screens.lock.presentation.viewModels

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aspectchat.core.data._nonPersistEncryptionKey
import com.example.aspectchat.core.data.datastore.UserAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

@HiltViewModel
class LockScreenViewModel @Inject constructor(
    userAccountDataStore: DataStore<UserAccount>,
) : ViewModel(

) {
    val isOpen = _nonPersistEncryptionKey
        .zip(userAccountDataStore.data) { encryptionKey, userAccount ->
            encryptionKey != null
                    && userAccount.userId != null
                    && userAccount.nickname != null
                    && userAccount.password != null
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            false // Provide a non-null initial value
        )
}