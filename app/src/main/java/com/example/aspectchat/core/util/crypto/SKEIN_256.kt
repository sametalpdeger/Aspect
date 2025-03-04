package com.example.aspectchat.core.util.crypto

import org.bouncycastle.jcajce.provider.digest.Skein

object SKEIN_256 {
    fun hash(value: ByteArray): ByteArray {
        val digest256 = Skein.Digest_256_256()
        digest256.update(value, 0, value.size)
        val result = ByteArray(32)
        digest256.digest(result, 0, result.size)

        return result
    }
}