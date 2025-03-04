package com.example.aspectchat.core.util.crypto

import java.security.SecureRandom

fun generateSalt16Byte(): ByteArray {
    val secureRandom = SecureRandom()
    val salt = ByteArray(16)
    secureRandom.nextBytes(salt)

    return salt
}
