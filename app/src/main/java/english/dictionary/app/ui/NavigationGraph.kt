package english.dictionary.app.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import english.dictionary.app.screen.home.HomeScreen
import english.dictionary.app.screen.home.HomeViewModel
import english.dictionary.app.screen.profile.ProfileScreen
import english.dictionary.app.screen.search.SearchScreen
import english.dictionary.app.ui.bottomNavigation.BottomNavItem

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String = BottomNavItem.HomeBottomNavItem.route,
) {
    NavHost(navController = navController, startDestination = startDestination){
        composable(BottomNavItem.HomeBottomNavItem.route){
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(viewModel)
        }
        composable(BottomNavItem.SearchBottomNavItem.route){
            SearchScreen()
        }
        composable(BottomNavItem.ProfileBottomNavItem.route){
            ProfileScreen()
        }
    }
}