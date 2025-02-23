package com.example.aspectchat.navigation.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aspectchat.navigation.domain.model.IsDrawerOpenState
import com.example.aspectchat.screens.friends.FriendsScreen
import com.example.aspectchat.screens.home.HomeScreen
import com.example.aspectchat.screens.home.SettingsScreen
import kotlinx.serialization.Serializable

@Composable
fun NavHosting(
    navController: NavHostController,
    drawerState: IsDrawerOpenState,
    onDrawerOpen: () -> Unit,
) {
    val drawerStateR = remember(drawerState) {
        drawerState
    }

    val navControllerR = remember(navController) {
        navController
    }

    NavHost(
        navController = navControllerR,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(
                drawerState = drawerStateR,
                onDrawerOpen = onDrawerOpen,
            )
        }

        composable<Settings> {
            SettingsScreen(
                drawerState = drawerStateR,
            )
        }

        composable<Friends> {
            FriendsScreen(
                drawerState = drawerStateR,
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