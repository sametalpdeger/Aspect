package com.example.aspectchat.core.util.crypto

import org.bouncycastle.jcajce.provider.digest.Keccak


object Keccak_256 {
    fun hash(value: ByteArray): ByteArray {
        val digest256 = Keccak.Digest256()
        return digest256.digest(value)
    }
}