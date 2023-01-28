package english.dictionary.app.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import english.dictionary.app.screen.home.HomeScreen
import english.dictionary.app.screen.home.HomeViewModel
import english.dictionary.app.screen.input_information.GetUserInformationScreen
import english.dictionary.app.screen.profile.ProfileScreen
import english.dictionary.app.screen.search.SearchScreen
import english.dictionary.app.screen.splash.AnimatedSplashScreen
import english.dictionary.app.screen.word.WordScreen
import english.dictionary.app.ui.bottomNavigation.Screen
import english.dictionary.app.util.AppSettings
import english.dictionary.app.util.dataStore
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String = Screen.SplashScreen.route,
    showShowRuntimePermission_voiceRecord: () -> Unit,
    onBackHandledToHome: () -> Unit
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
            HomeScreen(
                viewModel
            )
        }
        composable(Screen.SearchScreen.route) {
            SearchScreen(
                onWordItemClicked = { navController.navigate(Screen.WordDetail.route) },
                showShowRuntimePermission_voiceRecord = { showShowRuntimePermission_voiceRecord() })
            BackHandler {
                val currentRoute = it.destination.route
                if (currentRoute == Screen.SearchScreen.route) onBackHandledToHome()
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(Screen.HomeScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen()
            BackHandler {
                val currentRoute = it.destination.route
                if (currentRoute == Screen.ProfileScreen.route) onBackHandledToHome()
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(Screen.HomeScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
        composable(Screen.WordDetail.route) {
            WordScreen()
        }
    }
}