package english.dictionary.app.ui.bottomNavigation

import english.dictionary.app.R


const val HOME = "home"
const val SPLASH = "splash"
const val PROFILE = "profile"
const val SEARCH = "search"

/**
 * @param route Guide for navHost that which destination must navigate
 */
sealed class Screen(
    val route: String,
    val activeIcon: Int,
    val disableIcon: Int,
    val title: String
) {

    object SplashScreen : Screen(SPLASH, -1,-1,"Splash")

    object HomeScreen : Screen(
        HOME,
        R.drawable.home_fill,
        R.drawable.home,
        "Home"
    )

    object SearchScreen : Screen(
        SEARCH,
        R.drawable.search_fill,
        R.drawable.search_normal,
        "Search"
    )

    object ProfileScreen :
        Screen(
            PROFILE,
            R.drawable.user_fill,
            R.drawable.user,
            "Profile"
        )
}
