package com.example.aspectchat.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.aspectchat.core.presentation.ui.theme.colorsA
import com.example.aspectchat.core.presentation.ui.theme.isDarkTheme
import com.example.aspectchat.screens.home.presentation.view.InputContent
import com.example.aspectchat.screens.home.presentation.view.Messages
import com.example.aspectchat.screens.home.presentation.view.TopNav

@Composable
fun HomeScreen(
    onDrawerOpen: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isDarkTheme) MaterialTheme.colorsA.Gray900 else MaterialTheme.colorsA.White2000),
        ) {


            TopNav(
                onDrawerOpen = onDrawerOpen
            )

            Messages(Modifier.weight(1f))
            InputContent()
        }
    }
}


