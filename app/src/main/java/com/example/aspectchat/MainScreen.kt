package com.example.aspectchat

import NavigationContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.aspectchat.screens.signup.SignUpScreen

@Composable
fun MainScreen() {
    var lockScreenOpen by rememberSaveable { mutableStateOf(true) }

    if (lockScreenOpen) {
        SignUpScreen {
            lockScreenOpen = false
        }
    } else NavigationContent()
}