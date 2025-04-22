package com.example.aspectchat.core.presentation.composes

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aspectchat.core.presentation.ui.theme.Colors


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenu(
    options: List<String>,
    onValueChange: (String) -> Unit,
    selectedDropdownItemTextStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    dropdownMenuItemModifier: Modifier = Modifier,
    dropdownMenuItemTextStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    label: String = "Label",
    labelStyle: TextStyle = MaterialTheme.typography.labelMedium,
    @SuppressLint("ModifierParameter") textfieldModifier: Modifier = Modifier
        .clip(RoundedCornerShape(size = 10.dp))
        .background(Colors.AspectBlue100),
    textFieldColors: TextFieldColors = ExposedDropdownMenuDefaults.textFieldColors(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
    ),
) {
    // Ensure options list is not empty
    val rememberedOptions = remember { options }
    var expanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(rememberedOptions.firstOrNull() ?: "") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        TextField(
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                .then(textfieldModifier),
            value = text,
            textStyle = selectedDropdownItemTextStyle,
            onValueChange = { /* No-op since it's read-only */ },
            readOnly = true,
            singleLine = true,
            label = { Text(label, style = labelStyle) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = textFieldColors,
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            rememberedOptions.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option, style = dropdownMenuItemTextStyle) },
                    onClick = {
                        text = option
                        expanded = false
                        onValueChange(option) // Notify parent of the change
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    modifier = dropdownMenuItemModifier
                )
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xff4330f3, heightDp = 400, widthDp = 400)
@Composable
fun ExposedDropdownMenuPreview() {
    ExposedDropdownMenu(
        options = listOf("Option 1", "Option 2", "Option 3"),
        onValueChange = {},
    )
}