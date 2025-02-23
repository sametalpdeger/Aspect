package com.example.aspectchat

import NavigationContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import kotlin.math.roundToInt

@Composable
fun MainScreen() {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current.density
    val screenWidth = remember(configuration, density) {
        (configuration.screenWidthDp * density).roundToInt()
    }





    NavigationContent(

    )
}