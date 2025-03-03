package com.example.aspectchat.navigation.main.bottomNavbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.aspectchat.R
import com.example.aspectchat.core.presentation.ui.theme.colorsA
import com.example.aspectchat.navigation.main.view.Friends
import com.example.aspectchat.navigation.main.view.Home
import com.example.aspectchat.navigation.main.view.Settings

@Composable
fun BottomNavbarView(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRouteStr = navBackStackEntry?.destination?.route?.substringAfterLast(".")

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorsA.Gray750)
    ) {
        Row(
            modifier = Modifier
                .navigationBarsPadding()
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            BottomNavbarItem(
                navController = navController,
                icon = R.drawable.home,
                name = "Home",
                navigateTo = Home,
                currentRoute = currentRouteStr
            )

            BottomNavbarItem(
                navController = navController,
                icon = R.drawable.friends,
                name = "Friends",
                navigateTo = Friends,
                currentRoute = currentRouteStr
            )

            BottomNavbarItem(
                navController = navController,
                icon = R.drawable.settings,
                name = "Settings",
                navigateTo = Settings,
                currentRoute = currentRouteStr
            )
        }
    }
}