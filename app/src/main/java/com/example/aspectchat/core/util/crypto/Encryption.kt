package com.example.aspectchat.core.util.crypto

import java.nio.charset.StandardCharsets
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

    private val cipher = Cipher.getInstance(TRANSFORMATION)
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
        TIGER_192
    }


    fun deriveAESKeyFromHash(
        text: String,
        hashAlgorithmToDeriveKey: HashAlgorithmToDeriveKey
    ): SecretKey {
        val hashedBytes = when (hashAlgorithmToDeriveKey) {
            HashAlgorithmToDeriveKey.SHA3_256 -> SHA3_256.hash(text.toByteArray(StandardCharsets.UTF_8))
            HashAlgorithmToDeriveKey.KECCAK_256 -> Keccak_256.hash(text.toByteArray(StandardCharsets.UTF_8))
            HashAlgorithmToDeriveKey.BLAKE3_256 -> BLAKE3_256.hash(text.toByteArray(StandardCharsets.UTF_8))
            HashAlgorithmToDeriveKey.SHAKE_256 -> SHAKE_256.hash(text.toByteArray(StandardCharsets.UTF_8))
            HashAlgorithmToDeriveKey.SKEIN_256 -> SKEIN_256.hash(text.toByteArray(StandardCharsets.UTF_8))
            HashAlgorithmToDeriveKey.RIPEMD_256 -> RIPEMD_256.hash(text.toByteArray(StandardCharsets.UTF_8))
            HashAlgorithmToDeriveKey.GOST_256 -> GOST_256.hash(text.toByteArray(StandardCharsets.UTF_8))
            HashAlgorithmToDeriveKey.TIGER_192 -> TIGER_192.hash(text.toByteArray(StandardCharsets.UTF_8))

        }

        return SecretKeySpec(hashedBytes, "AES")
    }

    fun encrypt(
        data: ByteArray,
        secretKey: SecretKey
    ): ByteArray? {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec)
        return cipher.doFinal(data)
    }

    fun decrypt(
        data: ByteArray,
        secretKey: SecretKey
    ): ByteArray? {
        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec)
        return cipher.doFinal(data)
    }
}