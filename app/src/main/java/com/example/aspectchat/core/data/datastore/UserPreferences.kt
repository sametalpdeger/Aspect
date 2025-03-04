package com.example.aspectchat.core.data.datastore

import androidx.datastore.core.Serializer
import kotlinx.serialization.Serializable
import java.io.InputStream
import java.io.OutputStream

@Serializable
data class UserPreferences(
    val darkMode: Boolean = false,
)

object UserPreferencesSerializer : Serializer<UserPreferences> {
    override val defaultValue: UserPreferences
        get() = UserPreferences()

    override suspend fun readFrom(input: InputStream): UserPreferences =
        readFromFunc(input, "UserPreferences")

    override suspend fun writeTo(t: UserPreferences, output: OutputStream) =
        writeToFunc(t, output, "UserPreferences")
}