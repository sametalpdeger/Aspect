package com.example.aspectchat.core.data

import kotlinx.coroutines.flow.MutableStateFlow
import javax.crypto.SecretKey

val _nonPersistEncryptionKey = MutableStateFlow<SecretKey?>(null)