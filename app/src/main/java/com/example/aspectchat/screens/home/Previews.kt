package com.example.aspectchat.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aspectchat.navigation.domain.model.IsDrawerOpenState


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        modifier = Modifier,
        drawerState = IsDrawerOpenState.Closed,
        onDrawerOpen = {}
    )
}