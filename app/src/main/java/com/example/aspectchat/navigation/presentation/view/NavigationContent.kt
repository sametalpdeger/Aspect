import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.compose.rememberNavController
import com.example.aspectchat.core.presentation.ui.theme.colorsA
import com.example.aspectchat.core.presentation.ui.theme.isDarkTheme
import com.example.aspectchat.navigation.bottomNavbar.BottomNavbarView
import com.example.aspectchat.navigation.domain.model.IsDrawerOpenState
import com.example.aspectchat.navigation.domain.model.isOpened
import com.example.aspectchat.navigation.navigationDrawer.presentation.view.Drawer
import com.example.aspectchat.navigation.presentation.view.MainContent
import kotlin.math.roundToInt

@Composable
fun NavigationContent() {
    val navController = rememberNavController()
    var drawerState by rememberSaveable { mutableStateOf(IsDrawerOpenState.Closed) }
    var isDrawerContentUnder by rememberSaveable { mutableStateOf(true) }
    var offsetX by rememberSaveable { mutableFloatStateOf(0f) }
    var isDragging by rememberSaveable { mutableStateOf(false) }
    var dragStartX by rememberSaveable { mutableFloatStateOf(0f) }

    val configuration = LocalConfiguration.current
    val density = LocalDensity.current.density

    val screenWidth = remember {
        derivedStateOf { (configuration.screenWidthDp * density).roundToInt() }
    }
    val maxOffsetX = remember {
        derivedStateOf { screenWidth.value / 1.5f }
    }
    val drawerWidthFraction = remember {
        derivedStateOf { 1f / 1.5f } // This will give us ~66% of screen width
    }

    val maxOffsetY = 100.dp
    val maxOffsetYPx = with(LocalDensity.current) { maxOffsetY.toPx() }

    // Animated offset value with completion callback
    val animatedOffsetX by animateFloatAsState(
        targetValue = offsetX,
        label = "offset animation",
        finishedListener = { finalValue ->
            if (finalValue == maxOffsetX.value) {
                drawerState = IsDrawerOpenState.Opened
                isDrawerContentUnder = false
            }
        }
    )

    // Animated progress value
    val animatedDragProgress = remember(animatedOffsetX) {
        derivedStateOf { (animatedOffsetX / maxOffsetX.value).coerceIn(0f, 1f) }
    }

    fun openDrawer() {
        offsetX = maxOffsetX.value
    }

    fun closeDrawer() {
        isDrawerContentUnder = true
        drawerState = IsDrawerOpenState.Closed
        offsetX = 0f
    }

    // Update offset when drawer state changes
    LaunchedEffect(drawerState) {
        if (!isDragging) {
            offsetX = if (drawerState.isOpened()) maxOffsetX.value else 0f
        }
    }

    Box(
        modifier = Modifier
            .background(
                if (isDarkTheme) MaterialTheme.colorsA.Gray800
                else MaterialTheme.colorsA.White900
            )
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .zIndex(if (isDrawerContentUnder) 1f else 3f)
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Drawer(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(drawerWidthFraction.value),
                )

                BottomNavbarView(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(maxOffsetX.value - 10),
                    navController
                )
            }
        }


        MainContent(
            navController = navController,
            onClick = {
                println("ON DRAG: ON CLICK")
                if (drawerState.isOpened()) closeDrawer()
            },
            onDragStart = {
                isDragging = true
                dragStartX = offsetX
                isDrawerContentUnder = true
                println("ON DRAG START: offsetX: $offsetX, dragStartX: $dragStartX")
            },
            onDragEnd = {
                isDragging = false

                if (drawerState.isOpened()) {
                    println("ON DRAG: drawer open")
                    println("ON DRAG: dragStartX Calculation: ${dragStartX - 200}")
                    val isEnoughToSwipe = (dragStartX - 100) > offsetX
                    println("ON DRAG END: offsetX: $offsetX, dragStartX: $dragStartX")

                    if (isEnoughToSwipe) { // Left swipe while opened -> close
                        println("ON DRAG END: isEnoughToSwipe offsetX: $offsetX, dragStartX: $dragStartX")
                        closeDrawer()
                    } else { // Right swipe while opened -> stay opened
                        println("ON DRAG END: !!!isNotEnoughToSwipe offsetX: $offsetX, dragStartX: $dragStartX")
                        openDrawer()
                    }
                } else {
                    println("ON DRAG: drawer closed")

                    val isEnoughToSwipe = (dragStartX + 100) < offsetX
                    println("ON DRAG END: offsetX: $offsetX, dragStartX: $dragStartX")

                    if (isEnoughToSwipe) { // Right swipe while closed -> open
                        openDrawer()
                    } else { // Left swipe while closed -> stay closed
                        println("ON DRAG END: !!!isNotEnoughToSwipe offsetX: $offsetX, dragStartX: $dragStartX")
                        closeDrawer()
                    }
                }
            },

            onDragCancel = {
                println("ON DRAG CANCEL: offsetX: $offsetX, dragStartX: $dragStartX")
                isDragging = false
                offsetX = if (drawerState.isOpened()) maxOffsetX.value else 0f
            },

            onDrag = { change, dragAmount ->
                change.consume()
                // Calculate new offset and constrain it
                val newOffset =
                    (offsetX + dragAmount).coerceIn(0f, maxOffsetX.value)
                // Only update if within bounds
                if (newOffset >= 0 && newOffset <= maxOffsetX.value) {
                    offsetX = newOffset
                }

                println("ON DRAG: offsetX: $offsetX, dragStartX: $dragStartX")
            },
            isDrawerContentUnder = isDrawerContentUnder,
            animatedOffsetX = animatedOffsetX,
            animatedDragProgress = animatedDragProgress.value,
            maxOffsetYPx = maxOffsetYPx,
            drawerState = drawerState,
            openDrawer = { openDrawer() }
        )
    }
}

