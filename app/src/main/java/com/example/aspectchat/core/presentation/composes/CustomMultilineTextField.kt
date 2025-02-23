package com.example.aspectchat.core.presentation.composes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle

@Composable
fun MultilineTextField(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    hintText: String = "",
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    maxLines: Int = Int.MAX_VALUE,
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        maxLines = maxLines,
        decorationBox = { innerTextField ->
            Box(modifier = Modifier) {
                if (value.isEmpty()) {
                    Text(
                        text = hintText,
                        color = textStyle.color.copy(alpha = 0.5f),
                    )
                }

                innerTextField()
            }
        },
        modifier = modifier
            .onFocusChanged {
                if (it.isFocused) {

                }
            }
    )
}