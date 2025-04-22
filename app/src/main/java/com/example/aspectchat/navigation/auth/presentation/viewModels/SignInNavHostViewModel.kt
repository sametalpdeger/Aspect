package com.example.aspectchat.navigation.auth.presentation.viewModels

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import com.example.aspectchat.core.data.datastore.UserAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SignInNavHostViewModel @Inject constructor(
    userAccountDataStore: DataStore<UserAccount>,
) : ViewModel() {
    val _isOpen = userAccountDataStore
        .data
        .map {
            it.userId == null || it.nickname == null || it.password == null
        }
}