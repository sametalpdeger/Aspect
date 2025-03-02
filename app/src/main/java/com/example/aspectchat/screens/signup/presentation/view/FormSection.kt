package com.example.aspectchat.screens.signup.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aspectchat.R
import com.example.aspectchat.core.presentation.composes.MultilineTextField
import com.example.aspectchat.core.presentation.composes.SinglelineTextField
import com.example.aspectchat.core.presentation.ui.theme.Colors
import com.example.aspectchat.core.presentation.ui.theme.outfitFontFamily


@Composable
fun FormSection(
    onContinue: () -> Unit,
    onChangeNickname: (String) -> Unit,
    onChangeEncryptionKey: (String) -> Unit,
    nickname: String,
    encryptionKey: String,
    isLoading: Boolean,
    error: Boolean,
    setSuccessful: () -> Unit
) {
    SinglelineTextField(
        value = nickname,
        placeholder = "Nickname",
        onValueChange = { onChangeNickname(it) },
        enabled = !isLoading,
        modifier = Modifier
            .clip(RoundedCornerShape(size = 30.dp)),
        decorationBoxModifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 10.dp)
    )

    Spacer(modifier = Modifier.height(10.dp))

    MultilineTextField(
        value = encryptionKey,
        enabled = !isLoading,
        onValueChange = { onChangeEncryptionKey(it) },
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
        onClick = { onContinue() },
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
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(30.dp),
                        color = Colors.White,
                        strokeWidth = 3.dp,


                        )
                } else Image(
                    painter = painterResource(id = R.drawable.arrow_right_in),
                    contentDescription = "icon",
                    modifier = Modifier.size(30.dp)
                )


            }
        }
    }
}
