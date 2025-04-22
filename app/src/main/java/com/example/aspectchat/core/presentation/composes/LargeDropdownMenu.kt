package com.example.aspectchat.core.presentation.composes

import android.annotation.SuppressLint
import androidx.compose.animation.core.EaseOutQuart
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.aspectchat.core.presentation.ui.theme.Colors
import com.example.aspectchat.core.presentation.ui.theme.outfitFontFamily

@Composable
fun <T> LargeDropdownMenu(
    @SuppressLint("ModifierParameter") textFieldContainerModifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String,
    notSetLabel: String? = null,
    items: List<T>,
    textFieldColors: TextFieldColors = TextFieldDefaults.colors(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
    ),
    selectedTextStyle: TextStyle = TextStyle(
        color = Colors.Gray0,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = outfitFontFamily
    ),
    labelStyle: TextStyle = TextStyle(
        color = Colors.Gray0,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = outfitFontFamily
    ),
    selectedIndex: Int = -1,
    onItemSelected: (index: Int, item: T) -> Unit,
    selectedItemToString: (T) -> String = { it.toString() },
    drawItem: @Composable (T, Boolean, Boolean, () -> Unit) -> Unit = { item, selected, itemEnabled, onClick ->
        LargeDropdownMenuItem(
            text = item.toString(),
            selected = selected,
            enabled = itemEnabled,
            onClick = onClick,
        )
    },
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = textFieldContainerModifier
            .height(IntrinsicSize.Min)
            .width(IntrinsicSize.Min)
    ) {
        BasicTextField(
            textStyle = selectedTextStyle,
            value = items.getOrNull(selectedIndex)?.let { selectedItemToString(it) } ?: "",
            enabled = enabled,
            readOnly = true,
            singleLine = true,
            decorationBox = { innerTextField ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(1.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 5.dp),
                ) {
                    Text(
                        text = label,
                        style = labelStyle,
                    )

                    innerTextField()
                }

            },
            modifier = textFieldModifier
                .clip(RoundedCornerShape(12.dp)),
            onValueChange = { },

            )


        // Transparent clickable surface on top of OutlinedTextField
        Surface(
            modifier = Modifier
                .clip(MaterialTheme.shapes.extraSmall)
                .clickable(enabled = enabled) { expanded = true }
                .fillMaxSize(),
            color = Color.Transparent,
        ) {}
    }

    if (expanded) {
        var isVisible by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            isVisible = true
        }

        val offset by animateFloatAsState(
            targetValue = if (isVisible) 0f else -100f,
            animationSpec = tween(100, easing = EaseOutQuart),
            label = "offset"
        )

        val alpha by animateFloatAsState(
            targetValue = if (isVisible) 1f else 0f,
            animationSpec = tween(100),
            label = "alpha",
            finishedListener = { if (!isVisible) expanded = false }
        )

        Dialog(
            onDismissRequest = {
                isVisible = false
            }
        ) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(10.dp)
                    .graphicsLayer(
                        alpha = alpha,
                        translationY = offset
                    )
            ) {
                val listState = rememberLazyListState()
                if (selectedIndex > -1) {
                    LaunchedEffect("ScrollToSelected") {
                        listState.scrollToItem(index = selectedIndex)
                    }
                }

                LazyColumn(modifier = Modifier.fillMaxWidth(), state = listState) {
                    if (notSetLabel != null) {
                        item {
                            LargeDropdownMenuItem(
                                text = notSetLabel,
                                selected = false,
                                enabled = false,
                                onClick = {
                                    isVisible = false
                                },
                            )
                        }
                    }
                    itemsIndexed(items) { index, item ->
                        val selectedItem = index == selectedIndex
                        drawItem(
                            item,
                            selectedItem,
                            true
                        ) {
                            onItemSelected(index, item)
                            isVisible = false  // Trigger exit animation
                        }

                        if (index < items.lastIndex) {
                            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LargeDropdownMenuItem(
    text: String,
    selected: Boolean,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    val contentColor = when {
        !enabled -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0f)
        selected -> MaterialTheme.colorScheme.primary.copy(alpha = 1f)
        else -> MaterialTheme.colorScheme.onSurface.copy(alpha = 1f)
    }

    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Box(modifier = Modifier
            .clickable(enabled) { onClick() }
            .fillMaxWidth()
            .padding(16.dp)) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}

@Preview
@Composable
fun LargeDropdownMenuPreview() {
    var selectedIndex by remember { mutableStateOf(-1) }

    LargeDropdownMenu(
        label = "Sample",
        items = listOf(
            "Item 1",
            "Item 2",
            "Item 3",
            "Item 4",
            "Item 5",
            "Item 6",
            "Item 7",
            "Item 8",
            "Item 9",
            "Item 10",
            "Item 11",
            "Item 12"
        ),
        selectedIndex = selectedIndex,
        onItemSelected = { index, _ -> selectedIndex = index },
    )
}