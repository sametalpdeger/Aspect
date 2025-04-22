package com.example.aspectchat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aspectchat.core.presentation.composes.LargeDropdownMenu
import com.example.aspectchat.core.presentation.ui.theme.Colors

@Composable
fun MainScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.AspectBlue)

    ) {
//        ExposedDropdownMenu(
//            label = "Cake flavor",
//            labelStyle = TextStyle(
//                color = Colors.AspectBlue,
//                fontSize = 17.sp,
//                fontWeight = FontWeight.SemiBold,
//                fontFamily = outfitFontFamily
//            ),
//            options = listOf("Chocolate", "Vanilla", "Strawberry"),
//            textFieldColors = TextFieldDefaults.colors(
//                focusedContainerColor = Colors.White,
//                unfocusedContainerColor = Colors.White,
//                disabledContainerColor = Colors.White,
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent
//            ),
//            selectedDropdownItemTextStyle = TextStyle(
//                color = Colors.Gray0,
//                fontSize = 15.sp,
//                fontWeight = FontWeight.SemiBold,
//                fontFamily = outfitFontFamily
//            ),
//            dropdownMenuItemModifier = Modifier.background(Colors.White),
//            onValueChange = {},
//        )

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

//    AuthNavigationContent {
    //      MainNavigationContent()
    // }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}