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
        R.drawable.baseline_home_24,
        R.drawable.baseline_home_24,
        "Home"
    )

    object SearchBottomNavItem : BottomNavItem(
        SEARCH,
        R.drawable.baseline_search_24,
        R.drawable.baseline_search_24,
        "Search"
    )

    object ProfileBottomNavItem :
        BottomNavItem(
            PROFILE,
            R.drawable.baseline_verified_user_24,
            R.drawable.baseline_verified_user_24,
            "Profile"
        )
}
