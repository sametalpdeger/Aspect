package com.example.aspectchat.core.data.datastore

import androidx.datastore.core.Serializer
import kotlinx.serialization.Serializable
import java.io.InputStream
import java.io.OutputStream

@Serializable
data class UserKeys(
    val encryptionKey: String? = null
)

object UserKeysSerializer : Serializer<UserKeys> {
    override val defaultValue: UserKeys
        get() = UserKeys()

    override suspend fun readFrom(input: InputStream): UserKeys = readFromFunc(input)
    override suspend fun writeTo(t: UserKeys, output: OutputStream) = writeToFunc(t, output)
}