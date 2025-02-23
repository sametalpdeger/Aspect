package com.example.aspectchat.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.aspectchat.core.presentation.ui.theme.colorsA
import com.example.aspectchat.core.presentation.ui.theme.isDarkTheme
import com.example.aspectchat.navigation.domain.model.IsDrawerOpenState
import com.example.aspectchat.screens.home.presentation.view.InputContent
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

            val paddingValues = WindowInsets.systemBars.asPaddingValues()

            Column(
                modifier = Modifier
                    .background(Color(0xff272a31))
            ) {
                Spacer(modifier = Modifier.height(paddingValues.calculateTopPadding()))
                TopNav(
                    onDrawerOpen = onDrawerOpen
                )
            }

            InputContent()
        }
    }
}


