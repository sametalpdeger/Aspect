package com.example.aspectchat.core.util.crypto

import org.bouncycastle.crypto.generators.Argon2BytesGenerator
import org.bouncycastle.crypto.params.Argon2Parameters

/**
 * Argon2 Hash object for hashing and verifying data like passwords.
 */
object Argon2 {
    private const val ITERATIONS = 2
    private const val HASH_LENGTH = 256
    private const val PARALLELISM = 1

    fun generateHash(value: ByteArray): ByteArray {
        val salt: ByteArray = generateSalt16Byte()
        val generate = Argon2BytesGenerator()
        val argon2Builder = Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
            .withVersion(Argon2Parameters.ARGON2_VERSION_13)
            .withParallelism(PARALLELISM)
            .withIterations(ITERATIONS)
            .withSalt(salt)

        generate.init(argon2Builder.build())
        val result = ByteArray(HASH_LENGTH)
        generate.generateBytes(value, result, 0, result.size)

        return result
    }

    /**
     * Verifies a hash against a value. Given salt should same as used before.
     */
    fun verifyHash(value: ByteArray, hash: ByteArray, salt: ByteArray): Boolean {
        val argon2Builder = Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
            .withVersion(Argon2Parameters.ARGON2_VERSION_13)
            .withParallelism(PARALLELISM)
            .withIterations(ITERATIONS)
            .withSalt(salt)

        val verifier = Argon2BytesGenerator()
        verifier.init(argon2Builder.build())
        val testHash = ByteArray(HASH_LENGTH)

        verifier.generateBytes(
            value,
            testHash,
            0,
            testHash.size
        )

        return testHash.contentEquals(hash)
    }
}


