package com.example.aspectchat


import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertTrue
import org.bouncycastle.crypto.generators.Argon2BytesGenerator
import org.bouncycastle.crypto.params.Argon2Parameters
import org.junit.Test
import java.nio.charset.StandardCharsets
import java.security.SecureRandom


private fun generateSalt16Byte(): ByteArray {
    val secureRandom = SecureRandom()
    val salt = ByteArray(16)
    secureRandom.nextBytes(salt)

    return salt
}

@HiltAndroidTest
class Argon2Test {
    @Test
    fun givenRawPasswordAndSalt_whenArgon2AlgorithmIsUsed_thenHashIsCorrect() {
        val salt: ByteArray = generateSalt16Byte()
        val password = "Baeldung"

        val iterations = 2
        val memLimit = 66536
        val hashLength = 32
        val parallelism = 1

        val builder = Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
            .withVersion(Argon2Parameters.ARGON2_VERSION_13)
            .withIterations(iterations)
            .withMemoryAsKB(memLimit)
            .withParallelism(parallelism)
            .withSalt(salt)

        val generate = Argon2BytesGenerator()
        generate.init(builder.build())
        val result = ByteArray(hashLength)
        generate.generateBytes(password.toByteArray(StandardCharsets.UTF_8), result, 0, result.size)

        val verifier = Argon2BytesGenerator()
        verifier.init(builder.build())
        val testHash = ByteArray(hashLength)
        verifier.generateBytes(
            password.toByteArray(StandardCharsets.UTF_8),
            testHash,
            0,
            testHash.size
        )

        assertTrue(result.contentEquals(testHash))
    }
}