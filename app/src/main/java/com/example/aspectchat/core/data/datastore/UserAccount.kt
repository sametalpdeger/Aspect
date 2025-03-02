package com.example.aspectchat.core.data.datastore

import androidx.datastore.core.Serializer
import kotlinx.serialization.Serializable
import java.io.InputStream
import java.io.OutputStream

@Serializable
data class UserAccount(
    val userId: String? = null,
    val nickname: String? = null,
    val password: String? = null,
)

object UserAccountSerializer : Serializer<UserAccount> {
    override val defaultValue: UserAccount
        get() = UserAccount()

    override suspend fun readFrom(input: InputStream): UserAccount = readFromFunc(input)
    override suspend fun writeTo(t: UserAccount, output: OutputStream) = writeToFunc(t, output)
}