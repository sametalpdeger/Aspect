package com.example.aspectchat.navigation.auth

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.aspectchat.navigation.auth.presentation.view.SignInNavHost
import com.example.aspectchat.navigation.auth.presentation.viewModels.SignInNavHostViewModel
import com.example.aspectchat.screens.lock.LockScreen
import com.example.aspectchat.screens.lock.presentation.viewModels.LockScreenViewModel

@Composable
fun AuthNavigationContent(
    content: @Composable () -> Unit
) {
    val lockScreenViewModel = hiltViewModel<LockScreenViewModel>()
    val isLockScreenOpen = lockScreenViewModel.isOpen.collectAsStateWithLifecycle()

    LockScreen(
        isOpen = isLockScreenOpen.value,
        setSuccessful = {},
    ) {
        val signInNavHostViewModel = hiltViewModel<SignInNavHostViewModel>()
        val isOpen = signInNavHostViewModel._isOpen.collectAsStateWithLifecycle(
            initialValue = null,
        )
        SignInNavHost(
            isOpen = isOpen.value,
        ) {
            content()
        }
    }
}