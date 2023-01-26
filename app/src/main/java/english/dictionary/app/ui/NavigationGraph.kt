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
import english.dictionary.app.screen.splash.AnimatedSplashScreen
import english.dictionary.app.screen.word.WordScreen
import english.dictionary.app.ui.bottomNavigation.Screen

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String = Screen.SplashScreen.route,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.SplashScreen.route) {
            AnimatedSplashScreen(onTimeOvered = {
                navController.popBackStack()
                navController.navigate(Screen.HomeScreen.route)
            })
        }
        composable(Screen.HomeScreen.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(viewModel)
        }
        composable(Screen.SearchScreen.route) {
            SearchScreen(onWordItemClicked = { navController.navigate(Screen.WordDetail.route) })
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen()
        }
        composable(Screen.WordDetail.route){
            WordScreen()
        }
    }
}