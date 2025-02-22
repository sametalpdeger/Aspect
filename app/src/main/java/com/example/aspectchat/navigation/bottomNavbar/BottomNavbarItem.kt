package com.example.aspectchat.navigation.bottomNavbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.aspectchat.R
import com.example.aspectchat.core.presentation.ui.theme.colorsA
import com.example.aspectchat.navigation.presentation.view.Home

@Composable
fun RowScope.BottomNavbarItem(
    name: String = "Home",
    navigateTo: Any = Home,
    icon: Int = R.drawable.home,
    navController: NavHostController,
    currentRoute: String?,
) {
    Button(
        onClick = { navController.navigate(navigateTo) },
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorsA.FullAlpha,
            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
            disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.38f)
        ),
        modifier = Modifier
            .weight(1f)
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = name,
            modifier = Modifier
                .size(25.dp)
                .alpha(if (currentRoute == name) 1f else 0.5f)
        )
    }
}