package com.example.aspectchat.core.util.crypto

import org.bouncycastle.jcajce.provider.digest.GOST3411;

object GOST_256 {
    fun hash(value: ByteArray): ByteArray {
        val messageDigest = GOST3411.Digest2012_256();
        messageDigest.update(value);
        return messageDigest.digest();
    }
}