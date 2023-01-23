package english.dictionary.app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import english.dictionary.app.ui.bottomNavigation.BottomNavItem

@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String = BottomNavItem.HomeBottomNavItem.route,
) {
    NavHost(navController = navController, startDestination = startDestination){
        composable(BottomNavItem.HomeBottomNavItem.route){
            HomeScreen()
        }
        composable(BottomNavItem.SearchBottomNavItem.route){
            SearchScreen()
        }
        composable(BottomNavItem.ProfileBottomNavItem.route){
            ProfileScreen()
        }
    }
}