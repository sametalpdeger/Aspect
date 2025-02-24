package com.example.aspectchat.screens.friends

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aspectchat.core.presentation.ui.theme.colorsA
import com.example.aspectchat.core.presentation.ui.theme.isDarkTheme
import com.example.aspectchat.screens.friends.presentation.view.AddFriend
import com.example.aspectchat.screens.friends.presentation.view.Friends
import com.example.aspectchat.screens.friends.presentation.view.TopNav

@Composable
fun FriendsScreen(
    onDrawerOpen: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .background(if (isDarkTheme) MaterialTheme.colorsA.Gray900 else MaterialTheme.colorsA.White2000)
    ) {
        TopNav(onDrawerOpen)
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp),
            modifier = Modifier
                .verticalScroll(scrollState)
                .weight(1f)
                .padding(20.dp)
        ) {
            AddFriend()
            Friends()
        }
    }
}