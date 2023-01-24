package english.dictionary.app.ui.bottomNavigation

import english.dictionary.app.R


const val HOME = "home"
const val SPLASH = "splash"
const val PROFILE = "profile"
const val SEARCH = "search"

/**
 * @param route Guide for navHost that which destination must navigate
 */
sealed class BottomNavItem(
    val route: String,
    val activeIcon: Int,
    val disableIcon: Int,
    val title: String
) {
    object HomeBottomNavItem : BottomNavItem(
        HOME,
        R.drawable.home_fill,
        R.drawable.home,
        "Home"
    )

    object SearchBottomNavItem : BottomNavItem(
        SEARCH,
        R.drawable.search_fill,
        R.drawable.search_normal,
        "Search"
    )

    object ProfileBottomNavItem :
        BottomNavItem(
            PROFILE,
            R.drawable.user_fill,
            R.drawable.user,
            "Profile"
        )
}
