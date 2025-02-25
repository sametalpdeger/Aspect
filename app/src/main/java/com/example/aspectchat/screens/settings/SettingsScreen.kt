package com.example.aspectchat.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aspectchat.R
import com.example.aspectchat.core.presentation.composes.MultilineTextField
import com.example.aspectchat.core.presentation.composes.TopNavTemplate
import com.example.aspectchat.core.presentation.ui.theme.Colors
import com.example.aspectchat.core.presentation.ui.theme.outfitFontFamily

@Composable
fun SettingsScreen(
    onDrawerOpen: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .background(Colors.Gray900)
            .fillMaxSize(),
    ) {
        TopNavTemplate(
            onDrawerOpen = onDrawerOpen
        ) {
            Text(
                text = "Settings",
                fontSize = 20.sp,
                fontFamily = outfitFontFamily,
                fontWeight = FontWeight.Medium,
                color = Colors.White
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
                .padding(20.dp)

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image3),
                    contentDescription = "settingsAvatar",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.height(12.dp))




                MultilineTextField(
                    value = "404nnnotfoundddd",
                    onValueChange = {},
                    textStyle = TextStyle
                        (
                        color = Colors.Gray200,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = outfitFontFamily,
                        textAlign = TextAlign.Center,
                    ),
                    expandBasedOnLineCount = false,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    height = 60.dp,
                )

                MultilineTextField(
                    value = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam hendrerit facilisis blandit. Etiam ultrices ligula tellus, a tristique ante volutpat vitae. Sed condimentum erat non ullamcorper elementum. Cras vel nibh tempor, pulvinar enim vitae, interdum metus. Donec auctor vitae orci quis malesuada. Aliquam erat volutpat. Nulla vestibulum volutpat nibh, a suscipit sem pharetra in. Aliquam porta diam ut nisi iaculis laoreet ut facilisis libero. Aenean quis dapibus est. Duis nibh augue, vestibulum bibendum magna a, ornare ultrices ligula.\n" +
                            "\n",
                    onValueChange = {},
                    placeholder = "Your encryption key here",
                    textStyle = TextStyle
                        (
                        color = Colors.Gray200,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = outfitFontFamily
                    ),
                    expandBasedOnLineCount = false,
                    modifier = Modifier
                        .clip(RoundedCornerShape(size = 20.dp))
                        .background(Colors.Gray750)
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                        .fillMaxWidth(),
                    height = 60.dp,
                )
            }
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen({})
}