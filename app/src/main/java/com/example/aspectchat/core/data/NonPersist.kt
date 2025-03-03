package com.example.aspectchat.core.data

import kotlinx.coroutines.flow.MutableStateFlow
import javax.crypto.SecretKey

val _nonPersistSecretKey = MutableStateFlow<SecretKey?>(null)