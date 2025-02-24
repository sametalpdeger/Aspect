package com.example.aspectchat.screens.home.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aspectchat.core.presentation.composes.MultilineTextField
import com.example.aspectchat.core.presentation.ui.theme.Colors
import com.example.aspectchat.screens.home.presentation.viewModels.InputViewModel

private val defaultModifiers = Modifier
    .background(Colors.Gray1000)
    .fillMaxWidth()
    .padding(horizontal = 15.dp, vertical = 10.dp)

@Composable
fun InputContent() {
    val inputViewModel: InputViewModel = viewModel()

    Row(
        modifier = Modifier
            .navigationBarsPadding()
            .padding(10.dp)
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
                        .then(defaultModifiers)

                    1 -> Modifier
                        .clip(RoundedCornerShape(size = 50.dp))
                        .then(defaultModifiers)

                    2 -> Modifier
                        .clip(RoundedCornerShape(size = 30.dp))
                        .then(defaultModifiers)

                    else -> Modifier
                        .clip(RoundedCornerShape(size = 20.dp))
                        .then(defaultModifiers)
                }
            },

            height = 35.dp,
            heightByLineCount = 20.dp,
            maxLineForExpansion = 5
        )
    }
}