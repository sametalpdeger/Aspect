package com.example.aspectchat.screens.friends.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aspectchat.core.presentation.ui.theme.Colors
import com.example.aspectchat.screens.friends.presentation.viewModels.Friend
import com.example.aspectchat.screens.friends.presentation.viewModels.FriendsViewModel

@Composable
fun Friends() {
    val friendsViewModel: FriendsViewModel = viewModel()

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Column {
            Text(
                text = "FRIENDS",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Colors.White
            )

            Text(
                text = "You can see your friends here",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Colors.Gray600
            )
        }

        LazyColumn(
            modifier = Modifier.height(500.dp), userScrollEnabled = false
        ) {
            items(
                items = friendsViewModel.friends,
                key = { it.id }
            ) { item ->
                Column {
                    FriendItem(
                        friend = item
                    )

                    Spacer(Modifier.height(10.dp))
                }
            }
        }
    }
}


@Composable
fun FriendItem(friend: Friend) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Image(
            painter = painterResource(friend.image),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
        )

        Text(
            text = friend.name,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            color = Colors.White,
            maxLines = 1
        )
    }
}