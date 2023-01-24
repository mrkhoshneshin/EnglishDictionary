package english.dictionary.app.ui.bottomNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigation(
    navController: NavHostController,
    backgroundColor: Color = Color.White,
    selectedItemColor: Color = Color.Blue,
    unselectedItemColor: Color = Color.DarkGray,

) {
    val items = listOf(
        BottomNavItem.HomeBottomNavItem,
        BottomNavItem.SearchBottomNavItem,
        BottomNavItem.ProfileBottomNavItem
    )
    BottomNavigation(backgroundColor = backgroundColor) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach {
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(if (it.route == currentRoute) it.activeIcon else it.disableIcon),
                        contentDescription = "icon"
                    )
                },
                selectedContentColor = selectedItemColor,
                unselectedContentColor = unselectedItemColor,
                selected = it.route == currentRoute,
                onClick = {
                    navController.navigate(it.route) {
                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}