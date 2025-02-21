package com.example.aspectchat.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.aspectchat.core.presentation.ui.theme.colorsA
import com.example.aspectchat.core.presentation.ui.theme.isDarkTheme
import com.example.aspectchat.navigation.navigationDrawer.domain.model.IsDrawerOpenState

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    drawerState: IsDrawerOpenState,
) {
    Column(
        modifier = modifier
            .background(if (isDarkTheme) MaterialTheme.colorsA.Gray900 else MaterialTheme.colorsA.White2000),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Setting",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorsA.White900
            )
        }
    }
}