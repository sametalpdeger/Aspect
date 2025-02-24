package com.example.aspectchat.screens.lock

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.aspectchat.core.presentation.ui.theme.Colors

@Composable
fun LockScreen() {
    val paddingValues = WindowInsets.systemBars.asPaddingValues()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.AspectBlue1100)
            .padding(20.dp),
    ) {
        Spacer(modifier = Modifier.height(paddingValues.calculateTopPadding()))

        Image(
            modifier = Modifier
                .size(130.dp),
            painter = painterResource(id = R.drawable.white_icon),
            contentDescription = "lock"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Decryption key",
            color = Colors.White2000,
            fontSize = 35.sp,
            fontWeight = FontWeight.SemiBold,
        )


        Text(
            text = "Please enter your decryption key. Without it we cannot show your encrypted data.",
            color = Colors.Gray100,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .widthIn(max = 350.dp, min = 0.dp)
                .padding(top = 20.dp),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(60.dp))

        Column(
            horizontalAlignment = Alignment.End
        ) {
            MultilineTextField(
                value = "",
                onValueChange = {},
                placeholder = "Enter your encryption key",
                textStyle = TextStyle(
                    color = Colors.AspectBlue1100,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W800,
                ),
                expandBasedOnLineCount = false,
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 20.dp))
                    .background(Colors.White)
                    .padding(10.dp)
                    .fillMaxWidth(),
                height = 60.dp,
            )

            Button(
                modifier = Modifier
                    .padding(top = 10.dp),
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Colors.AspectBlue200,
                    disabledContainerColor = Colors.AspectBlue100,
                ),
            ) {
                Text(
                    text = "Continue",
                    color = Colors.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }


    }
}


@Preview
@Composable
fun LockScreenPreview() {
    LockScreen()
}