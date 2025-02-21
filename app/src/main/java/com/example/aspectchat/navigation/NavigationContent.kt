import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.aspectchat.core.presentation.ui.theme.colorsA
import com.example.aspectchat.core.presentation.ui.theme.isDarkTheme
import com.example.aspectchat.core.util.coloredShadow
import com.example.aspectchat.navigation.bottomNavbar.BottomNavbarView
import com.example.aspectchat.navigation.navigationDrawer.domain.model.IsDrawerOpenState
import com.example.aspectchat.navigation.navigationDrawer.domain.model.isOpened
import com.example.aspectchat.navigation.navigationDrawer.presentation.view.Drawer
import kotlin.math.roundToInt

/**
 * Main navigation content composable that handles the drawer navigation system
 * with gesture support and animations.
 */
@Composable
fun NavigationContent() {
    val navController = rememberNavController()
    val navigationState = rememberNavigationState()
    val screenMetrics = rememberScreenMetrics()

    Box(
        modifier = Modifier
            .background(getBackgroundColor())
            .fillMaxSize()
    ) {
        DrawerContent(
            navigationState = navigationState,
            screenMetrics = screenMetrics,
            navController = navController
        )

        MainContent(
            navigationState = navigationState,
            screenMetrics = screenMetrics,
            navController = navController
        )
    }
}

/**
 * Holds the navigation state data
 */
@Composable
private fun rememberNavigationState() = remember {
    NavigationState(
        drawerState = mutableStateOf(IsDrawerOpenState.Closed),
        navContentUnder = mutableStateOf(true),
        offsetX = mutableFloatStateOf(0f),
        isDragging = mutableStateOf(false),
        dragStartX = mutableFloatStateOf(0f)
    )
}

/**
 * Holds screen metrics calculations
 */
@Composable
private fun rememberScreenMetrics(): ScreenMetrics {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current.density

    return remember {
        ScreenMetrics(
            screenWidth = derivedStateOf { (configuration.screenWidthDp * density).roundToInt() },
            maxOffsetX = derivedStateOf { (configuration.screenWidthDp * density).roundToInt() / 1.5f },
            drawerWidthFraction = derivedStateOf { 1f / 1.5f },
            maxOffsetY = 100.dp
        )
    }
}

@Composable
private fun DrawerContent(
    navigationState: NavigationState,
    screenMetrics: ScreenMetrics,
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .zIndex(if (navigationState.navContentUnder.value) 1f else 3f)
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Drawer(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(screenMetrics.drawerWidthFraction.value)
            )

            BottomNavbarView(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(screenMetrics.maxOffsetX.value - 10),
                navController = navController
            )
        }
    }
}

@Composable
private fun MainContent(
    navigationState: NavigationState,
    screenMetrics: ScreenMetrics,
    navController: NavHostController
) {
    val density = LocalDensity.current
    val maxOffsetYPx = with(density) { screenMetrics.maxOffsetY.toPx() }

    val animatedOffsetX = createAnimatedOffset(navigationState, screenMetrics)
    val animatedDragProgress = remember(animatedOffsetX) {
        derivedStateOf {
            (animatedOffsetX.value / screenMetrics.maxOffsetX.value).coerceIn(0f, 1f)
        }
    }

    Box(
        modifier = Modifier
            .zIndex(if (navigationState.navContentUnder.value) 3f else 1f)
            .fillMaxSize()
    ) {
        NavHosting(
            navController = navController,
            modifier = createMainContentModifier(
                navigationState = navigationState,
                screenMetrics = screenMetrics,
                animatedOffsetX = animatedOffsetX.value,
                animatedDragProgress = animatedDragProgress.value,
                maxOffsetYPx = maxOffsetYPx
            ),
            drawerState = navigationState.drawerState.value,
            onDrawerOpen = { navigationState.openDrawer(screenMetrics.maxOffsetX.value) }
        )
    }
}

@Composable
private fun createAnimatedOffset(
    navigationState: NavigationState,
    screenMetrics: ScreenMetrics
): State<Float> {
    return animateFloatAsState(
        targetValue = navigationState.offsetX.value,
        label = "offset animation",
        finishedListener = { finalValue ->
            if (finalValue == screenMetrics.maxOffsetX.value) {
                navigationState.drawerState.value = IsDrawerOpenState.Opened
                navigationState.navContentUnder.value = false
            }
        }
    )
}

@Composable
private fun createMainContentModifier(
    navigationState: NavigationState,
    screenMetrics: ScreenMetrics,
    animatedOffsetX: Float,
    animatedDragProgress: Float,
    maxOffsetYPx: Float
): Modifier {
    return Modifier
        .clickable(
            enabled = navigationState.drawerState.value.isOpened(),
            onClick = { navigationState.closeDrawer() }
        )
        .pointerInput(Unit) {
            detectHorizontalDragGestures(
                onDragStart = { navigationState.handleDragStart() },
                onDragEnd = { navigationState.handleDragEnd(screenMetrics.maxOffsetX.value) },
                onDragCancel = { navigationState.handleDragCancel(screenMetrics.maxOffsetX.value) },
                onHorizontalDrag = { change, dragAmount ->
                    navigationState.handleDrag(dragAmount, screenMetrics.maxOffsetX.value)
                    change.consume()
                }
            )
        }
        .graphicsLayer {
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
        )
}

@Composable
private fun getBackgroundColor(): Color {
    return if (isDarkTheme) MaterialTheme.colorsA.Gray800
    else MaterialTheme.colorsA.White900
}

/**
 * Data class to hold navigation-related state
 */
private data class NavigationState(
    val drawerState: MutableState<IsDrawerOpenState>,
    val navContentUnder: MutableState<Boolean>,
    val offsetX: MutableState<Float>,
    val isDragging: MutableState<Boolean>,
    val dragStartX: MutableState<Float>
) {
    fun openDrawer(maxOffset: Float) {
        offsetX.value = maxOffset
    }

    fun closeDrawer() {
        navContentUnder.value = true
        drawerState.value = IsDrawerOpenState.Closed
        offsetX.value = 0f
    }

    fun handleDragStart() {
        isDragging.value = true
        dragStartX.value = offsetX.value
        navContentUnder.value = true
    }

    fun handleDragEnd(maxOffset: Float) {
        isDragging.value = false
        val isOpened = drawerState.value.isOpened()
        val isEnoughToSwipe = if (isOpened) {
            dragStartX.value + 200 > offsetX.value
        } else {
            dragStartX.value + 200 < offsetX.value
        }

        when {
            isOpened && isEnoughToSwipe -> closeDrawer()
            !isOpened && isEnoughToSwipe -> openDrawer(maxOffset)
            isOpened -> openDrawer(maxOffset)
            else -> closeDrawer()
        }
    }

    fun handleDragCancel(maxOffset: Float) {
        isDragging.value = false
        offsetX.value = if (drawerState.value.isOpened()) maxOffset else 0f
    }

    fun handleDrag(dragAmount: Float, maxOffset: Float) {
        val newOffset = (offsetX.value + dragAmount).coerceIn(0f, maxOffset)
        if (newOffset >= 0 && newOffset <= maxOffset) {
            offsetX.value = newOffset
        }
    }
}

/**
 * Data class to hold screen metrics calculations
 */
private data class ScreenMetrics(
    val screenWidth: State<Int>,
    val maxOffsetX: State<Float>,
    val drawerWidthFraction: State<Float>,
    val maxOffsetY: Dp
)