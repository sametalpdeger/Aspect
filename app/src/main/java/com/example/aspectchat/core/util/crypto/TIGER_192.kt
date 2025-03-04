package com.example.aspectchat.core.util.crypto

import org.bouncycastle.jcajce.provider.digest.Tiger;

object TIGER_192 {
    fun hash(value: ByteArray): ByteArray {
        val messageDigest = Tiger.Digest()
        messageDigest.update(value)
        return messageDigest.digest()
    }
}