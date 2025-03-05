package com.example.aspectchat.core.util.crypto

import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * Encryption class for encryption and decryption in app.
 */
object Encryption {
    /**
     * The transformation used for encryption and decryption.
     *
     * AES - Advanced Encryption Standart is most secure algorithm for encryption.
     *
     * GCM - Galois Counter Mode is used for authentication and integrity.
     *
     *  NoPadding - No padding is not needed for encryption because it can
     * cause issues like padding oracle attacks.
     */
    private const val TRANSFORMATION = "AES/GCM/NoPadding"

    /** The length of the authentication tag used for GCM. */
    private const val TAG_LENGTH = 128

    /** The length of the initialization vector used for
     *  to ensure the randomness of the first encrypted
     *  block of data, preventing identical plaintexts
     *  from encrypting to the same ciphertext.
     * */
    private const val IV_LENGTH = 12

    private val ivParameterSpec = IvParameterSpec(ByteArray(IV_LENGTH))
    private val gcmParameterSpec = GCMParameterSpec(TAG_LENGTH, ivParameterSpec.iv)

    enum class HashAlgorithmToDeriveKey {
        SHA3_256,
        KECCAK_256,
        BLAKE3_256,
        SHAKE_256,
        RIPEMD_256,
        SKEIN_256,
        GOST_256,
        TIGER_128
    }


    fun deriveAESKeyFromHash(
        text: ByteArray,
        hashAlgorithmToDeriveKey: HashAlgorithmToDeriveKey
    ): SecretKey {
        val hashedBytes = when (hashAlgorithmToDeriveKey) {
            HashAlgorithmToDeriveKey.SHA3_256 -> SHA3_256.hash(text)
            HashAlgorithmToDeriveKey.KECCAK_256 -> Keccak_256.hash(text)
            HashAlgorithmToDeriveKey.BLAKE3_256 -> BLAKE3_256.hash(text)
            HashAlgorithmToDeriveKey.SHAKE_256 -> SHAKE_256.hash(text)
            HashAlgorithmToDeriveKey.SKEIN_256 -> SKEIN_256.hash(text)
            HashAlgorithmToDeriveKey.RIPEMD_256 -> RIPEMD_256.hash(text)
            HashAlgorithmToDeriveKey.GOST_256 -> GOST_256.hash(text)
            HashAlgorithmToDeriveKey.TIGER_128 -> TIGER_128.hash(text)
        }

        return SecretKeySpec(hashedBytes, "AES")
    }

    fun encrypt(
        data: ByteArray,
        secretKey: SecretKey
    ): ByteArray? {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec)

        return cipher.doFinal(data)
    }

    fun decrypt(
        data: ByteArray,
        secretKey: SecretKey
    ): ByteArray? {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec)
        return cipher.doFinal(data)
    }
}