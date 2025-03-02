package com.example.aspectchat

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.datastore.dataStore
import com.example.aspectchat.core.data.datastore.UserAccountSerializer
import com.example.aspectchat.core.data.datastore.UserKeysSerializer
import com.example.aspectchat.core.data.datastore.UserPreferencesSerializer
import com.example.aspectchat.core.presentation.ui.theme.AspectChatTheme
import dagger.hilt.android.AndroidEntryPoint

val Context.userPreferencesDataStore by dataStore(
    fileName = "user-preferences",
    serializer = UserPreferencesSerializer
)

val Context.userSecretsDataStore by dataStore(
    fileName = "user-secrets",
    serializer = UserKeysSerializer
)

val Context.userAccountDataStore by dataStore(
    fileName = "user-account",
    serializer = UserAccountSerializer
)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AspectChatTheme {
                MainScreen()
            }
        }
    }
}
