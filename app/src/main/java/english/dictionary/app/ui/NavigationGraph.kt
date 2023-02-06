package english.dictionary.app.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import english.dictionary.app.R
import english.dictionary.app.data.WordDetailData
import english.dictionary.app.screen.addBook.AddWordScreen
import english.dictionary.app.screen.book.BookViewModel
import english.dictionary.app.screen.book.BooksScreen
import english.dictionary.app.screen.favorite.FavoriteScreen
import english.dictionary.app.screen.favorite.FavoriteWordsViewModel
import english.dictionary.app.screen.home.HomeScreen
import english.dictionary.app.screen.home.HomeViewModel
import english.dictionary.app.screen.home.WebViewScreen
import english.dictionary.app.screen.home.data.FeatureEnum
import english.dictionary.app.screen.home.data.WebViewUrl
import english.dictionary.app.screen.inputInformation.InputInformationScreen
import english.dictionary.app.screen.profile.ProfileScreen
import english.dictionary.app.screen.search.AlphabeticSection
import english.dictionary.app.screen.search.SearchScreen
import english.dictionary.app.screen.search.WordsList
import english.dictionary.app.screen.splash.AnimatedSplashScreen
import english.dictionary.app.screen.word.WordScreen
import english.dictionary.app.ui.bottomNavigation.Screen
import english.dictionary.app.ui.common.CustomTextField
import english.dictionary.app.ui.common.Header

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
                viewModel,
                onProfileIconClicked = { navController.navigate(Screen.ProfileScreen.route) },
                onFeatureItemClicked = {
                    when (it.id) {
                        FeatureEnum.FLASH_CARD -> navController.navigate(Screen.FavoriteWords.route)
                        FeatureEnum.GOOGLE_TRANSLATE -> {
                            WebViewUrl.value = viewModel.getGoogleTranslateUrl()
                            navController.navigate(Screen.WebViewScreen.route)
                        }
                        FeatureEnum.GRAMMARLY -> {
                            WebViewUrl.value = viewModel.getGrammerlyUrl()
                            navController.navigate(Screen.WebViewScreen.route)
                        }
                    }
                },
                onPageItemClicked = {
                    WebViewUrl.value = it.url
                    navController.navigate(Screen.WebViewScreen.route)
                },
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
            ProfileScreen(
                onAddNewWordButtonClicked = { navController.navigate(Screen.AddWord.route) },
                onOnlineListButtonClicked = {},
                onUpdateInformationClicked = {
                    navController.navigate(Screen.InputInformation.route)
                })
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

        composable(Screen.BooksScreen.route) {
            val viewModel: BookViewModel = hiltViewModel()
            BooksScreen(
                viewModel.getBooks(),
                onBookItemClicked = {
                    WebViewUrl.value = it.url
                    navController.navigate(Screen.WebViewScreen.route)
                })
        }
        composable(Screen.FavoriteWords.route) {
            val viewModel: FavoriteWordsViewModel = hiltViewModel()
            val textFieldValue = viewModel.textFieldValue.collectAsState().value
            val selectedAlphabet = viewModel.selectedAlphabet.collectAsState().value
            val favoriteWords = viewModel.favoriteWords.collectAsState().value
            val emptyState = favoriteWords.size == 0
            FavoriteScreen(
                emptyState = emptyState,
                header = {
                    Header(
                        title = stringResource(id = R.string.favorite_words),
                        leftIcon = null,
                        rightIcon = null,
                        onLeftIconClicked = { },
                        onRightIconClicked = {})
                },
                searchBox = {
                    CustomTextField(
                        leadingIcon = { Icon(painter = painterResource(id = R.drawable.heart_search), contentDescription = "")},
                        label = stringResource(id = R.string.searchInFavorites),
                        textFieldValue = textFieldValue,
                        onTextFieldTextChanged = { viewModel.onTextFieldTextChanged(it) },
                        onSearchIconClicked = { }) {
                    }
                },
                alphabets = {
                    AlphabeticSection(
                        modifier = Modifier.padding(bottom = 50.dp),
                        onCharacterItemChanged = {
                            viewModel.onSelectedAlphabetChanged(it)
                        },
                        selectedChar = selectedAlphabet
                    )
                },
                words = {
                    WordsList(words = favoriteWords,
                        onWordItemClicked = {
                            WordDetailData.word = it
                            navController.navigate(Screen.WordDetail.route)
                        },
                        scrollPosition = if (selectedAlphabet.isEmpty()) null else favoriteWords.indexOfFirst { word ->
                            word.englishTitle?.startsWith(
                                selectedAlphabet
                            ) ?: false
                        })

                }, viewModel = viewModel
            )
        }
        composable(Screen.WebViewScreen.route) {
            WebViewScreen(url = WebViewUrl.value)
        }

        composable(Screen.InputInformation.route) {
            InputInformationScreen(onSaveButtonClicked = { navController.popBackStack() })
        }
        composable(Screen.AddWord.route) {
            AddWordScreen(onSaveButtonClicked = { navController.popBackStack() })
        }
    }
}