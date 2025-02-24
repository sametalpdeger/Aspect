package com.example.aspectchat.screens.home.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aspectchat.R
import com.example.aspectchat.core.presentation.composes.MultilineTextField
import com.example.aspectchat.core.presentation.ui.theme.Colors
import com.example.aspectchat.screens.home.presentation.viewModels.InputViewModel

private val modifiersAfterClip = Modifier
    .background(Colors.Gray1000)
    .padding(horizontal = 15.dp, vertical = 10.dp)


@Composable
fun InputContent() {
    val inputViewModel: InputViewModel = viewModel()

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .navigationBarsPadding()
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        MultilineTextField(
            value = inputViewModel.input.value,
            onValueChange = { inputViewModel.setInput(it) },
            placeholder = "Type a message",
            textStyle = TextStyle(
                color = Colors.White2000,
                fontSize = 18.sp,
            ),
            expandBasedOnLineCount = true,
            modifiersBasedOnLineCount = { lineCount ->
                when (lineCount) {
                    0 -> Modifier
                        .clip(RoundedCornerShape(size = 99.dp))
                        .then(modifiersAfterClip)

                    1 -> Modifier
                        .clip(RoundedCornerShape(size = 50.dp))
                        .then(modifiersAfterClip)

                    2 -> Modifier
                        .clip(RoundedCornerShape(size = 30.dp))
                        .then(modifiersAfterClip)

                    else -> Modifier
                        .clip(RoundedCornerShape(size = 20.dp))
                        .then(modifiersAfterClip)
                }
            },
            modifier = Modifier.weight(1f),
            height = 35.dp,
            heightByLineCount = 20.dp,
            maxLineForExpansion = 5
        )

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Colors.AspectBlue900)
                .padding(start = 12.dp, end = 8.dp, top = 10.dp, bottom = 10.dp)


        ) {
            Image(
                painter = painterResource(R.drawable.send),
                contentDescription = "send message vector",
                modifier = Modifier
                    .size(25.dp)
            )
        }
    }
}