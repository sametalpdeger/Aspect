package com.example.aspectchat

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
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
    fun givenRawPasswordAndSalt_whenArgon2AlgorithmIsUsed_thenHashIsCorrect() {
        val salt: ByteArray = generateSalt16Byte()
        val password = "Baeldung"

        val iterations = 2
        val hashLength = 256
        val parallelism = 1

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

        println("RESULT: $result")
        println("TEST HASH: ${testHash.toHexString()}")
        println("SALT: $salt")
        println("PASSWORD: $password")
        println("ITERATIONS: $iterations")
        println("HASH LENGTH: $hashLength")
        println("PARALLELISM: $parallelism")

        assertTrue(result.contentEquals(testHash))
    }

}