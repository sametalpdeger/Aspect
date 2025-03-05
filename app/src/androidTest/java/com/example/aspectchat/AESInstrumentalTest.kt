package com.example.aspectchat

import com.example.aspectchat.core.util.crypto.Encryption
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.nio.charset.StandardCharsets

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@HiltAndroidTest
class AESInstrumentalTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun givenRawPassword_whenUsedToEncrypt_thenShouldWork() {
        val password = "password"
        val otherstring = "Filanaaaaaaaaaaaaae"
        val encryptionType = Encryption.HashAlgorithmToDeriveKey.TIGER_128
        val secretKey = Encryption.deriveAESKeyFromHash(
            password.toByteArray(StandardCharsets.UTF_8),
            encryptionType
        )
        val secretKey3 = Encryption.deriveAESKeyFromHash(
            otherstring.toByteArray(StandardCharsets.UTF_8),
            encryptionType
        )

        val encryptedData = Encryption.encrypt(password.toByteArray(), secretKey)

        println("ENCRYPTED DATA: ${encryptedData?.toHexString()}")
        val decryptedData = Encryption.decrypt(encryptedData!!, secretKey)

        println("DECRYPTED DATA: ${decryptedData?.toHexString()}")
        assertEquals(password, String(decryptedData!!))
    }


}