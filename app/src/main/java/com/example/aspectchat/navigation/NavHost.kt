import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aspectchat.navigation.navigationDrawer.domain.model.IsDrawerOpenState
import com.example.aspectchat.screens.friends.FriendsScreen
import com.example.aspectchat.screens.home.HomeScreen
import com.example.aspectchat.screens.home.SettingsScreen
import kotlinx.serialization.Serializable

@Composable
fun NavHosting(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    drawerState: IsDrawerOpenState,
    onDrawerOpen: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(
                modifier = modifier,
                drawerState = drawerState,
                onDrawerOpen = onDrawerOpen,
            )
        }

        composable<Settings> {
            SettingsScreen(
                modifier = modifier,
                drawerState = drawerState,
            )
        }

        composable<Friends> {
            FriendsScreen(
                modifier = modifier,
                drawerState = drawerState,
            )
        }
    }
}

@Serializable
object Home

@Serializable
object Settings

@Serializable
object Friends