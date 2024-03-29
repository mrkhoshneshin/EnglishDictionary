package english.dictionary.app.ui.bottomNavigation

import english.dictionary.app.R


const val HOME = "home"
const val SPLASH = "splash"
const val PROFILE = "profile"
const val SEARCH = "search"
const val WORD_DETAIL = "word_detail"
const val GET_USER_INFORMATION = "getUserInformation"
const val BOOKS = "books"
const val FAVORITE_WORDS = "favorite_words"
const val WEB_VIEW = "webView"
const val ADD_WORD = "addWord"

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
    object InputInformation: Screen(GET_USER_INFORMATION,-1,-1,"getUserInformation")
    object SplashScreen : Screen(SPLASH, -1, -1, "Splash")

    object AddWord: Screen(ADD_WORD, -1,-1,"addWord")
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

    object BooksScreen : Screen(
        BOOKS,
        R.drawable.book_fill,
        R.drawable.book_outline,
        "Book",
        index = 2
    )

    object ProfileScreen :
        Screen(
            PROFILE,
            R.drawable.user_fill,
            R.drawable.user,
            "Profile",
            index = -1
        )

    object FavoriteWords : Screen(
        FAVORITE_WORDS,
        -1,
        -1,
        "favorite words",
    )

    object WebViewScreen: Screen(
        WEB_VIEW,
        -1,-1,"webView"
    )
}
