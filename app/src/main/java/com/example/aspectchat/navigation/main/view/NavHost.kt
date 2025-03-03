package com.example.aspectchat.navigation.main.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aspectchat.screens.friends.FriendsScreen
import com.example.aspectchat.screens.home.HomeScreen
import com.example.aspectchat.screens.settings.SettingsScreen
import kotlinx.serialization.Serializable

@Composable
fun NavHosting(
    navController: NavHostController,
    onDrawerOpen: () -> Unit,
) {
    val navControllerR = remember(navController) {
        navController
    }

    NavHost(
        navController = navControllerR,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(
                onDrawerOpen = onDrawerOpen,
            )
        }

        composable<Settings> {
            SettingsScreen(
                onDrawerOpen = onDrawerOpen,
            )
        }

        composable<Friends> {
            FriendsScreen(
                onDrawerOpen = onDrawerOpen,
            )
        }
    }
}

@Serializable
object Home

@Serializable
object Settings

@Serializable
object Friends