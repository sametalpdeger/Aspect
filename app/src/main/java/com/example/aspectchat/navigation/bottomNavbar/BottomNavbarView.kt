package com.example.aspectchat.navigation.bottomNavbar

import Friends
import Home
import Settings
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.aspectchat.R
import com.example.aspectchat.core.presentation.ui.theme.colorsA

@Composable
fun BottomNavbarView(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRouteStr = navBackStackEntry?.destination?.route
    var orientation by remember { mutableIntStateOf(Configuration.ORIENTATION_PORTRAIT) }
    val configuration = LocalConfiguration.current

// If our configuration changes then this will launch a new coroutine scope for it
    LaunchedEffect(configuration) {
        // Save any changes to the orientation value on the configuration object
        snapshotFlow { configuration.orientation }
            .collect { orientation = it }
    }

    println("currentRouteStr: $currentRouteStr")

    when (orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
        }

        else -> {
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorsA.Gray750)
            .height(if (orientation === Configuration.ORIENTATION_PORTRAIT) 15.dp else 25.dp)
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