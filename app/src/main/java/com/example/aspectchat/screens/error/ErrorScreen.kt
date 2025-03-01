package com.example.aspectchat.screens.error


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aspectchat.R
import com.example.aspectchat.core.presentation.ui.theme.Colors
import com.example.aspectchat.core.presentation.ui.theme.outfitFontFamily

@Composable
fun ErrorScreen(
    errorCode: String,
    errorTitle: String,
    errorMessage: String,
) {
    val paddingValues = WindowInsets.systemBars.asPaddingValues()
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.ErrorRed)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(20.dp),
        ) {
            Spacer(modifier = Modifier.height(paddingValues.calculateTopPadding()))

            Image(
                modifier = Modifier
                    .size(130.dp),
                painter = painterResource(id = R.drawable.white_icon),
                contentDescription = "icon"
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = errorTitle,
                color = Colors.White2000,
                fontSize = 35.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = outfitFontFamily
            )

            Text(
                text = """Code: $errorCode""",
                color = Colors.Gray100,
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = outfitFontFamily
            )


            Text(
                text = errorMessage,
                color = Colors.Gray100,
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .widthIn(max = 350.dp, min = 0.dp)
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center,
                fontFamily = outfitFontFamily
            )
        }
    }
}


@Preview
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(
        errorCode = "001",
        errorTitle = "Too many attempts",
        errorMessage = "You have entered the wrong key too many times. You have one more chance to enter the key after 24 hours.",
    )
}