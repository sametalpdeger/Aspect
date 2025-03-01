package com.example.aspectchat.screens.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aspectchat.R
import com.example.aspectchat.core.presentation.composes.MultilineTextField
import com.example.aspectchat.core.presentation.composes.SinglelineTextField
import com.example.aspectchat.core.presentation.ui.theme.Colors
import com.example.aspectchat.core.presentation.ui.theme.outfitFontFamily

@Composable
fun SignUpScreen(
    setSuccessful: () -> Unit,
) {
    val paddingValues = WindowInsets.systemBars.asPaddingValues()
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.AspectBlue)
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
                    .size(110.dp),
                painter = painterResource(id = R.drawable.white_icon),
                contentDescription = "lock"
            )
            Spacer(modifier = Modifier.height(60.dp))


            Text(
                text = "Sign up",
                color = Colors.White,
                fontSize = 34.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = outfitFontFamily
            )


            Spacer(modifier = Modifier.height(30.dp))

            FormSection { setSuccessful() }

            Text(
                text = """
                 
                """.trimIndent(),
                color = Color(0xcaffffff),
                fontSize = 12.sp,
                lineHeight = 17.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .widthIn(max = 350.dp, min = 0.dp)
                    .padding(top = 15.dp),
                fontFamily = outfitFontFamily
            )

        }
    }


}

@Composable
fun FormSection(setSuccessful: () -> Unit) {
    SinglelineTextField(
        placeholder = "Nickname",
        onValueChange = {},
        modifier = Modifier
            .clip(RoundedCornerShape(size = 30.dp)),
        decorationBoxModifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 10.dp)
    )

    Spacer(modifier = Modifier.height(10.dp))

    MultilineTextField(
        value = "",
        onValueChange = {},
        placeholder = "Your encryption key here",
        textStyle = TextStyle(
            color = Colors.Gray0,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = outfitFontFamily
        ),
        expandBasedOnLineCount = false,
        height = 60.dp,
        decorationBoxModifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(size = 12.dp))
            .background(Colors.White),
    )

    Spacer(modifier = Modifier.height(20.dp))

    Button(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(0.dp),
        onClick = { setSuccessful() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Colors.AspectBlue1100,
            disabledContainerColor = Colors.FullAlpha,
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(size = 30.dp))
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            Color(0xFF5D4BFF), Color(
                                0xFF8376FF
                            ), Color(0xFF5D4BFF)
                        )
                    )
                )
                .padding(horizontal = 20.dp, vertical = 10.dp)

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Continue",
                    color = Colors.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = outfitFontFamily
                )
                Spacer(modifier = Modifier.width(14.dp))
                Image(
                    painter = painterResource(id = R.drawable.arrow_right_in),
                    contentDescription = "icon",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun SignUpScreenPreview() {
    SignUpScreen({})
}

