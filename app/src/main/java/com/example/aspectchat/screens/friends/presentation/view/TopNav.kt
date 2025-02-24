package com.example.aspectchat.screens.friends.presentation.view

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.aspectchat.core.presentation.composes.TopNavTemplate
import com.example.aspectchat.core.presentation.ui.theme.colorsA

@Composable
fun TopNav(onDrawerOpen: () -> Unit) {
    TopNavTemplate(
        onDrawerOpen
    ) {
        Text(
            text = "Friends",
            color = MaterialTheme.colorsA.White2000,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f)
        )
    }
}