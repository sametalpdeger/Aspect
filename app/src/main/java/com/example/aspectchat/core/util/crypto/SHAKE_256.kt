package com.example.aspectchat.core.util.crypto

import org.bouncycastle.crypto.digests.SHAKEDigest

object SHAKE_256 {
    fun hash(value: ByteArray): ByteArray {
        val digest256 = SHAKEDigest()
        digest256.update(value, 0, value.size)
        val result = ByteArray(32)
        digest256.doFinal(result, 0, result.size)

        return result
    }
}