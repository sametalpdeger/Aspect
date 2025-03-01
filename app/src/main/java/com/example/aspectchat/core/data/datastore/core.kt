package com.example.aspectchat.core.data.datastore

import android.util.Base64
import com.example.aspectchat.core.util.Crypto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

suspend inline fun <reified T> readFromFunc(input: InputStream, encryption: Boolean = true): T {
    val encryptedBytes = withContext(Dispatchers.IO) {
        input.use { it.readBytes() }
    }
    val decodedBytes = Base64.decode(encryptedBytes, Base64.DEFAULT)
    val decryptedBytes =
        if (encryption)
            Crypto.decrypt(decodedBytes) ?: throw Exception("DataStore Decryption failed")
        else decodedBytes

    val decodedJsonString = decryptedBytes.toString(Charsets.UTF_8)

    return Json.decodeFromString(decodedJsonString)
}

suspend inline fun <reified T> writeToFunc(t: T, output: OutputStream, encryption: Boolean = true) {
    val json = Json.encodeToString(t)

    val bytes = json.toByteArray()
    val encryptedBytes = if (encryption) Crypto.encrypt(bytes)
        ?: throw Exception("DataStore Encryption failed")
    else bytes

    val resultBytes = Base64.encode(encryptedBytes, Base64.DEFAULT)

    withContext(Dispatchers.IO) {
        output.use {
            it.write(resultBytes)
        }
    }
}