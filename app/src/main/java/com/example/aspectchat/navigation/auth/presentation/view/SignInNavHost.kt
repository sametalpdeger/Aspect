package com.example.aspectchat.navigation.auth.presentation.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aspectchat.screens.signin.SignInScreen
import com.example.aspectchat.screens.signup.SignUpScreen
import kotlinx.serialization.Serializable


@Composable
fun SignInNavHost(
    isOpen: Boolean?,
    content: @Composable () -> Unit
) {

    if (isOpen == null || !isOpen) return content()


    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SignUp
    ) {
        composable<SignUp> {
            SignUpScreen()
        }

        composable<SignIn> {
            SignInScreen(
                {}
            )
        }
    }
}

@Serializable
object SignUp

@Serializable
object SignIn
