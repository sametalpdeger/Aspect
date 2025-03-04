package com.example.aspectchat.screens.signup.presentation.viewModels

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import com.example.aspectchat.core.data.datastore.UserAccount
import com.example.aspectchat.core.data.repositories.MainServerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FormSectionViewModel @Inject constructor(
    private val mainServerRepository: MainServerRepository,
    private val userAccountDataStore: DataStore<UserAccount>,
) : ViewModel() {
    val _isLoading = MutableStateFlow(false)
    val _isError = MutableStateFlow(false)
    val _nickname = MutableStateFlow("")
    val _encryptionKey = MutableStateFlow("")

    fun setNickname(nickname: String) {
        _nickname.value = nickname
    }

    fun setEncryptionKey(encryptionKey: String) {
        _encryptionKey.value = encryptionKey
    }

    fun onContinue() {
        CoroutineScope(Dispatchers.IO).launch {
            if (
                _nickname.value.trim().isEmpty() ||
                _encryptionKey.value.trim().isEmpty()
            ) return@launch

            _isLoading.value = true
            val response = mainServerRepository.getSignUpResponse()

            if (response !== null) {
                _isLoading.value = false
                _isError.value = false

                userAccountDataStore.updateData {
                    UserAccount(
                        userId = response.userID,
                        nickname = _nickname.value,
                        password = response.secretID
                    )
                }

                println("RESPONSE: $response")
            } else {
                _isLoading.value = false
                _isError.value = true

                println("RESPONSE: $response")
            }
        }
    }
}