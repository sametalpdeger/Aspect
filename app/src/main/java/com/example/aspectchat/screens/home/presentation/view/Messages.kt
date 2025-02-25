package com.example.aspectchat.screens.home.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aspectchat.core.presentation.ui.theme.colorsA
import com.example.aspectchat.core.presentation.ui.theme.outfitFontFamily
import com.example.aspectchat.screens.home.data.model.Message
import com.example.aspectchat.screens.home.presentation.viewModels.MessagesViewModel

@Composable
fun Messages(modifier: Modifier = Modifier) {
    val messagesViewModel: MessagesViewModel = viewModel()
    val messages = messagesViewModel.messages.collectAsStateWithLifecycle()

    Box(modifier = modifier) {
        MessageList(
            messages = messages.value
        )
    }
}

@Composable
fun MessageList(
    messages: List<Message>,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier,
        contentPadding = PaddingValues(
            vertical = 16.dp,
        )
    ) {
        items(items = messages, key = { it.id }) { message ->
            MessageItem(
                message = message
            )
        }
    }
}


@Composable
fun MessageItem(
    message: Message,
) {
    Box(
        contentAlignment = if (message.isMyMessage) Alignment.CenterEnd else Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                end = if (message.isMyMessage) 15.dp else 0.dp,
                start = if (message.isMyMessage) 0.dp else 15.dp
            )

    ) {
        Box(
            contentAlignment = if (message.isMyMessage) Alignment.CenterEnd else Alignment.CenterStart,
            modifier = Modifier
                .requiredWidth((LocalConfiguration.current.screenWidthDp.dp * 0.7f) - 20.dp)

        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 20.dp))
                    .background(if (message.isMyMessage) MaterialTheme.colorsA.AspectBlue1100 else MaterialTheme.colorsA.AspectBlue600)
                    .padding(10.dp)
            ) {
                Text(
                    text = message.message,
                    color = MaterialTheme.colorsA.White900,
                    fontFamily = outfitFontFamily,
                    fontSize = 15.sp,
                )
            }

        }

    }
}