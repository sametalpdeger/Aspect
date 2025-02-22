package com.example.aspectchat.navigation.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.aspectchat.core.util.coloredShadow
import com.example.aspectchat.navigation.domain.model.IsDrawerOpenState


@Composable
fun MainContent(
    navController: NavHostController,
    isDrawerContentUnder: Boolean,
    animatedOffsetX: Float,
    animatedDragProgress: Float,
    maxOffsetYPx: Float,
    drawerState: IsDrawerOpenState,
    onClick: () -> Unit,
    onDragStart: (offset: Offset) -> Unit,
    onDragEnd: () -> Unit,
    onDragCancel: () -> Unit,
    onDrag: (change: PointerInputChange, dragAmount: Float) -> Unit,
    openDrawer: () -> Unit,
) {
    Box(
        modifier = Modifier
            .zIndex(if (isDrawerContentUnder) 3f else 1f)
            .fillMaxSize()
    ) {
        NavHosting(
            navController = navController,
            modifier = Modifier
                .clickable { onClick() }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragStart = { offset -> onDragStart(offset) },
                        onDragEnd = { onDragEnd() },
                        onDragCancel = { onDragCancel() },
                        onHorizontalDrag = { change, dragAmount -> onDrag(change, dragAmount) },
                    )
                }
                .graphicsLayer {
                    // Apply transformations based on animated values
                    translationX = animatedOffsetX
                    translationY = -(maxOffsetYPx * animatedDragProgress)
                    scaleX = 1f - (0.1f * animatedDragProgress)
                    scaleY = 1f - (0.1f * animatedDragProgress)
                    shape = RoundedCornerShape(20.dp * animatedDragProgress)
                    clip = true
                }
                .coloredShadow(
                    color = Color.Black,
                    alpha = 0.5f,
                    shadowRadius = 50.dp,
                    offsetX = 10.dp,
                    offsetY = 10.dp
                ),
            drawerState = drawerState,
            onDrawerOpen = { openDrawer() },
        )
    }
}