package com.example.aspectchat.screens.home.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aspectchat.R
import com.example.aspectchat.core.presentation.composes.TopNavTemplate
import com.example.aspectchat.core.presentation.ui.theme.colorsA
import com.example.aspectchat.core.presentation.ui.theme.outfitFontFamily


@Composable
fun TopNav(
    onDrawerOpen: () -> Unit,
) {
    TopNavTemplate(onDrawerOpen) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(R.drawable.image3),
                contentDescription = null,
                modifier = Modifier
                    .size(37.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "404nnotfounddddddddddddddddddd404nnotfounddddddddddddddddddd404nnotfounddddddddddddddddddd404nnotfounddddddddddddddddddd404nnotfounddddddddddddddddddd404nnotfounddddddddddddddddddd404nnotfoundddddddddddddddddddd".substring(
                    0..11
                ),
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = outfitFontFamily,
                color = MaterialTheme.colorsA.White2000,
                maxLines = 1
            )
        }

        Spacer(modifier = Modifier.width(20.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(17.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(R.drawable.video),
                contentDescription = null,
                modifier = Modifier
                    .size(27.dp)
                    .alpha(0.5f)
            )

            Image(
                painter = painterResource(R.drawable.call),
                contentDescription = null,
                modifier = Modifier
                    .size(26.dp)
                    .alpha(0.5f)
            )

            Image(
                painter = painterResource(R.drawable.options),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .alpha(0.8f)
            )
        }
    }
}