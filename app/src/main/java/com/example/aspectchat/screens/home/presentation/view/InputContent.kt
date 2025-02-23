package com.example.aspectchat.screens.home.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aspectchat.core.presentation.composes.MultilineTextField
import com.example.aspectchat.core.presentation.ui.theme.Colors
import com.example.aspectchat.screens.home.presentation.viewModels.InputViewModel

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
            hintText = "Type a message",
            textStyle = TextStyle(
                color = Colors.White2000,
            ),
            modifier = Modifier
                .height(50.dp)
                .clip(RoundedCornerShape(size = 99.dp))
                .background(Colors.Gray1000)
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .weight(1f)

        )
    }
}