package com.example.aspectchat.screens.signup.presentation.view

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aspectchat.R
import com.example.aspectchat.core.presentation.ui.theme.Colors
import com.example.aspectchat.core.presentation.ui.theme.outfitFontFamily


@Composable
fun MainLayout(
    content: @Composable () -> Unit,
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

            content()


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
