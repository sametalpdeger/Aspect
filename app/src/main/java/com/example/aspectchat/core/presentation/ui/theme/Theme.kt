package com.example.aspectchat.core.presentation.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.OverscrollConfiguration
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun AspectChatTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalAspectColors provides Colors,
        LocalOverscrollConfiguration provides OverscrollConfiguration(
            glowColor = Colors.AspectBlue700
        )
    ) {
        MaterialTheme(
            typography = Typography,
            content = content
        )
    }
}

val LocalAspectColors = staticCompositionLocalOf { Colors }
val MaterialTheme.colorsA: Colors
    @Composable
    @ReadOnlyComposable
    get() = LocalAspectColors.current

val LocalDarkTheme = compositionLocalOf { true }
val isDarkTheme: Boolean
    @Composable
    @ReadOnlyComposable
    get() = LocalDarkTheme.current


