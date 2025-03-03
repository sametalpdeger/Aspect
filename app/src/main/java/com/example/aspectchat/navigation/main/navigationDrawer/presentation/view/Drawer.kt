package com.example.aspectchat.navigation.main.navigationDrawer.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.aspectchat.R
import com.example.aspectchat.core.presentation.ui.theme.isDarkTheme

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
    )
    {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 12.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                modifier = Modifier
                    .size(80.dp),
                painter = painterResource(id = if (isDarkTheme) R.drawable.white_icon else R.drawable.icon_blue),
                contentDescription = "icon"
            )
            Spacer(modifier = Modifier.height(20.dp))
            NavigationItemsView()
            Spacer(modifier = Modifier.height(24.dp))
        }

    }
}
