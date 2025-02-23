package com.example.aspectchat.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.aspectchat.screens.home.data.model.Message
import com.example.aspectchat.screens.home.presentation.view.MessageItem


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onDrawerOpen = {}
    )
}

@Preview
@Composable
fun MessagesPreview() {
    MessageItem(
        message = Message(
            id = "1",
            userId = "Alice",
            message = "Hello",
            isMyMessage = true,
            time = 0
        )
    )
}