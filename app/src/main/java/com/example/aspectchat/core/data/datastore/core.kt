package com.example.aspectchat.core.data.datastore

import android.util.Base64
import com.example.aspectchat.core.data._nonPersistEncryptionKey
import com.example.aspectchat.core.util.crypto.Encryption
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

suspend inline fun <reified T> readFromFunc(input: InputStream, name: String): T {
    val encryptedBytes = withContext(Dispatchers.IO) {
        input.use { it.readBytes() }
    }
    val decodedBytes = Base64.decode(encryptedBytes, Base64.DEFAULT)
    val decryptedBytes = Encryption.decrypt(
        decodedBytes,
        _nonPersistEncryptionKey.value ?: throw Exception("DataStore Decryption failed: $name")
    ) ?: throw Exception("DataStore Decryption failed: $name")

    val decodedJsonString = decryptedBytes.toString(Charsets.UTF_8)

    return Json.decodeFromString(decodedJsonString)
}

suspend inline fun <reified T> writeToFunc(t: T, output: OutputStream, name: String) {
    val json = Json.encodeToString(t)

    val bytes = json.toByteArray()
    val encryptedBytes = Encryption.encrypt(
        bytes,
        _nonPersistEncryptionKey.value ?: throw Exception("DataStore Encryption failed: $name")
    ) ?: throw Exception("DataStore Encryption failed: $name")


    val resultBytes = Base64.encode(encryptedBytes, Base64.DEFAULT)

    withContext(Dispatchers.IO) {
        output.use {
            it.write(resultBytes)
        }
    }
}