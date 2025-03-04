package com.example.aspectchat

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.bouncycastle.crypto.generators.Argon2BytesGenerator
import org.bouncycastle.crypto.params.Argon2Parameters
import org.junit.Rule
import org.junit.Test
import java.nio.charset.StandardCharsets
import java.security.SecureRandom

private fun generateSalt16Byte(): ByteArray {
    val secureRandom = SecureRandom()
    val salt = ByteArray(16)
    secureRandom.nextBytes(salt)

    return salt
}

object Hash {
    private const val ITERATIONS = 2
    private const val HASH_LENGTH = 256
    private const val PARALLELISM = 1

    fun generateHash(value: String): ByteArray {
        val salt: ByteArray = generateSalt16Byte()
        val generate = Argon2BytesGenerator()
        val argon2Builder = Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
            .withVersion(Argon2Parameters.ARGON2_VERSION_13)
            .withParallelism(PARALLELISM)
            .withIterations(ITERATIONS)
            .withSalt(salt)

        generate.init(argon2Builder.build())
        val result = ByteArray(HASH_LENGTH)
        generate.generateBytes(value.toByteArray(StandardCharsets.UTF_8), result, 0, result.size)

        return result
    }

    fun verifyHash(value: String, hash: ByteArray): Boolean {
        val salt: ByteArray = generateSalt16Byte()
        val argon2Builder = Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
            .withVersion(Argon2Parameters.ARGON2_VERSION_13)
            .withParallelism(PARALLELISM)
            .withIterations(ITERATIONS)
            .withSalt(salt)

        val verifier = Argon2BytesGenerator()
        verifier.init(argon2Builder.build())
        val testHash = ByteArray(HASH_LENGTH)

        verifier.generateBytes(
            value.toByteArray(StandardCharsets.UTF_8),
            testHash,
            0,
            testHash.size
        )

        return testHash.contentEquals(hash)
    }
}

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@HiltAndroidTest
class Argon2InstrumentedTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun givenRawPassword_whenArgon2AlgorithmIsUsed_givesHash() {
        val password = "Baeldung"
        val hash = Hash.generateHash(password)
        println("HASH: ${hash.toHexString()}")
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun givenRawPasswordAndSalt_whenArgon2AlgorithmIsUsed_thenHashIsCorrect() {
        val salt: ByteArray = generateSalt16Byte()
        val password = "Baeldung"

        val iterations = 2
        val hashLength = 256
        val parallelism = 1

        println("SALT: $salt.toHexString()")
        println("SALT LENGTH: ${salt.size}")
        println("SALT BYTES: ${salt.toHexString()}")


        val builder = Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
            .withVersion(Argon2Parameters.ARGON2_VERSION_13)
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

        println("RESULT: ${result.toHexString()}")
        println("TEST HASH: ${testHash.toHexString()}")
        println("SALT: $salt")
        println("PASSWORD: $password")
        println("ITERATIONS: $iterations")
        println("HASH LENGTH: $hashLength")
        println("PARALLELISM: $parallelism")

        assertTrue(result.contentEquals(testHash))
    }


    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun new_givenRawPasswordAndSalt_whenArgon2AlgorithmIsUsed_thenHashIsCorrect() {
        val password = "Baeldung"
        val hash = Hash.generateHash(password)
        println("HASH: ${hash.toHexString()}")

        assertEquals(true, Hash.verifyHash(password, hash))
    }
}