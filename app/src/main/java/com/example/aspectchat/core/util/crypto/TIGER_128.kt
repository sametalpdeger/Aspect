package com.example.aspectchat.core.util.crypto

import org.bouncycastle.jcajce.provider.digest.Tiger;

object TIGER_128 {
    fun hash(value: ByteArray): ByteArray {
        val messageDigest = Tiger.Digest()
        messageDigest.update(value)
        val fullHash = messageDigest.digest();
        return fullHash.copyOf(16)
    }
}