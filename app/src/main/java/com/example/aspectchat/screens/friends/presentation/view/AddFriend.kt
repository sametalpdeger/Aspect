package com.example.aspectchat.screens.friends.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aspectchat.core.presentation.composes.MultilineTextField
import com.example.aspectchat.core.presentation.ui.theme.Colors
import com.example.aspectchat.core.presentation.ui.theme.colorsA

@Composable
fun ColumnScope.AddFriend() {
    Column(
        verticalArrangement = Arrangement.spacedBy(13.dp)

    ) {
        Row {
            Spacer(modifier = Modifier.width(2.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "ADD FRIEND",
                    color = MaterialTheme.colorsA.White2000,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = "You can add friends with their Aspect ID",
                    color = Colors.Gray350,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
        MultilineTextField(
            value = "",
            onValueChange = {},
            placeholder = "You can add friends with their Aspect ID",
            textStyle = TextStyle(
                color = MaterialTheme.colorsA.White2000,
                fontSize = 14.sp,
            ),
            expandBasedOnLineCount = false,
            modifier = Modifier
                .clip(RoundedCornerShape(size = 8.dp))
                .background(Colors.Gray800)
                .padding(10.dp)
                .fillMaxWidth(),
            height = 80.dp,
        )

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Colors.AspectBlue900)

        ) {
            Text(
                text = "Send request",
                color = MaterialTheme.colorsA.White2000,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}