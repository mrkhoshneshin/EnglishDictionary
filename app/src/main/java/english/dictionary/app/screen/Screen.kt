package english.dictionary.app.screen


const val HOME = "home"
const val SPLASH = "splash"
const val PROFILE = "profile"
const val SEARCH = "search"

/**
 * @param route Guide for navHost that which destination must navigate
 */
sealed class Screen(val route: String) {
    object HomeScreen : Screen(HOME)
    object SplashScreen : Screen(SPLASH)
    object SearchScreen : Screen(SEARCH)
    object ProfileScreen : Screen(PROFILE)
}
