package com.example.aspectchat.navigation.main.navigationDrawer.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aspectchat.core.presentation.ui.theme.colorsA
import com.example.aspectchat.navigation.main.model.UserData

@Composable
fun NavigationItemView(
    navigationItem: UserData,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(size = 99.dp))
            .clickable { onClick() }
            .background(
                color = if (selected) Color(0x16ffffff) else MaterialTheme.colorsA.FullAlpha,
                shape = RoundedCornerShape(99.dp)
            )
            .padding(horizontal = 6.dp, vertical = 6.dp)
            .alpha(if (selected) 1f else 0.5f),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = navigationItem.image),
            contentDescription = "Navigation Item Icon",
            tint = Color.Unspecified,
            modifier = Modifier
                .clip(RoundedCornerShape(size = 99.dp))
                .width(40.dp)
                .height(40.dp),
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = navigationItem.name,
            color = Color(0xb4ffffff),
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            lineHeight = 20.sp,
            fontSize = 20.sp
        )
    }
}