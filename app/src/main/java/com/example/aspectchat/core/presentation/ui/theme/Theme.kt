package com.example.aspectchat.core.presentation.ui.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AspectChatTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalRippleConfiguration provides null,
        LocalAspectColors provides Colors
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


