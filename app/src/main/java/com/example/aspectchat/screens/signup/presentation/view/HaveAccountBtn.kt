package com.example.aspectchat.screens.signup.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aspectchat.core.presentation.ui.theme.Colors
import com.example.aspectchat.core.presentation.ui.theme.outfitFontFamily

@Composable
fun HaveAccountBtn(
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier,
        contentPadding = PaddingValues(0.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Colors.FullAlpha,
            disabledContainerColor = Colors.FullAlpha,
        ),
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(size = 30.dp))
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            Color(0xFF5D4BFF), Color(0xFF6A5BFF), Color(0xFF5D4BFF)
                        )
                    )
                )
                .padding(horizontal = 20.dp, vertical = 10.dp)

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "I have an account",
                    color = Color(0xd3ffffff),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = outfitFontFamily
                )
            }
        }
    }
}

@Preview(showBackground = true,  backgroundColor = 0xff4330f3, heightDp = 400, widthDp = 400)
@Composable
fun HaveAccountBtnPreview() {
    HaveAccountBtn({})
}
