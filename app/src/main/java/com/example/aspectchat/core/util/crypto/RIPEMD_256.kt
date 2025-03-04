package com.example.aspectchat.core.util.crypto

import org.bouncycastle.jcajce.provider.digest.RIPEMD256;

object RIPEMD_256 {
    fun hash(value: ByteArray): ByteArray {
        val messageDigest = RIPEMD256.Digest()
        messageDigest.update(value)
        return messageDigest.digest()
    }
}