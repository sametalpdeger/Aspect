package com.example.aspectchat.core.util.crypto

import org.bouncycastle.jcajce.provider.digest.Blake3

object BLAKE3_256 {
    fun hash(value: ByteArray): ByteArray {
        val digest256 = Blake3.Blake3_256()
        return digest256.digest(value)
    }
}