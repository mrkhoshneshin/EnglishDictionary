package english.dictionary.app.ui.bottomNavigation

import english.dictionary.app.R


const val HOME = "home"
const val SPLASH = "splash"
const val PROFILE = "profile"
const val SEARCH = "search"
const val WORD_DETAIL = "word_detail"
const val GET_USER_INFORMATION = "getUserInformation"

/**
 * @param route Guide for navHost that which destination must navigate
 */
sealed class Screen(
    val route: String,
    val activeIcon: Int,
    val disableIcon: Int,
    val title: String,
    val index: Int = -1
) {

    object SplashScreen : Screen(SPLASH, -1, -1, "Splash")

    object WordDetail : Screen(WORD_DETAIL, -1, -1, "")
    object HomeScreen : Screen(
        HOME,
        R.drawable.home_fill,
        R.drawable.home,
        "Home",
        index = 0
    )

    object SearchScreen : Screen(
        SEARCH,
        R.drawable.search_fill,
        R.drawable.search_normal,
        "Search",
        index = 1
    )

    object ProfileScreen :
        Screen(
            PROFILE,
            R.drawable.user_fill,
            R.drawable.user,
            "Profile",
            index = 2
        )
}
