package com.example.aspectchat.core.util.crypto

import org.bouncycastle.jcajce.provider.digest.SHA3

object SHA3_256 {
    fun hash(value: ByteArray): ByteArray {
        val messageDigest = SHA3.Digest256()
        messageDigest.update(value)
        return messageDigest.digest()
    }
}