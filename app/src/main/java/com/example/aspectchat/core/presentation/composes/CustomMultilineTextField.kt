package com.example.aspectchat.core.presentation.composes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.aspectchat.core.presentation.ui.theme.Colors

@Composable
        /**
         * Custom Aspect composable for a multiline text field.
         * */
fun MultilineTextField(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    /** The placeholder text to be displayed when the text field is empty */
    placeholder: String = "",
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    expandBasedOnLineCount: Boolean = false,
    /** The height of the text field */
    height: Dp,
    /** The height will be expanded based on the line count of the text.
     * To use it "expandBasedOnLineCount" must be true.*/
    heightByLineCount: Dp = 56.dp,
    /** The maximum number of lines for which the height will be expanded.
     *  To use it "expandBasedOnLineCount" must be true.*/
    maxLineForExpansion: Int = Int.MAX_VALUE,
    maxLines: Int = Int.MAX_VALUE,
    /**
     * Allows applying custom modifiers based on the current line count of the text.
     * To use it "expandBasedOnLineCount" must be true.
     *
     * @param lineCount The current number of lines in the text field
     * @return A Modifier that will be applied to the TextField
     *
     * Example usage:
     * modifiersBasedOnLineCount = { lineCount ->
     *     when (lineCount) {
     *         1 -> Modifier.background(Color.White)
     *         2 -> Modifier.background(Color.Gray)
     *         else -> Modifier.background(Color.LightGray)
     *     }
     * }
     */
    modifiersBasedOnLineCount: (Int) -> Modifier = { Modifier },
    /**
     * The brush used to draw the cursor.
     */
    cursorBrush: SolidColor = SolidColor(Colors.AspectBlue1100),
) {
    var heightMultiplier by remember { mutableStateOf(0.dp) }
    var currentLineCount by remember { mutableIntStateOf(1) }


    // Base height for single paragraph
    fun handleOnValueChange(newValue: String) {
        onValueChange(newValue)
    }

    BasicTextField(
        value = value,
        onTextLayout = { textLayoutResult ->
            if (expandBasedOnLineCount) {
                currentLineCount = textLayoutResult.lineCount

                if (currentLineCount < maxLineForExpansion) {
                    val lineCount = textLayoutResult.lineCount
                    println("TextField: linecount $lineCount")

                    heightMultiplier = lineCount.dp * heightByLineCount.value
                }
            }
        },
        onValueChange = { value -> handleOnValueChange(value) },
        textStyle = textStyle,
        maxLines = maxLines,
        cursorBrush = cursorBrush,
        decorationBox = { innerTextField ->
            Box(modifier = Modifier) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = textStyle.color.copy(alpha = 0.5f),
                        fontSize = textStyle.fontSize
                    )
                }

                innerTextField()
            }
        },

        modifier = modifier
            .height(height + heightMultiplier)
            .then(modifiersBasedOnLineCount(currentLineCount))

    )
}