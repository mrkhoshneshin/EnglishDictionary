package english.dictionary.app

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import english.dictionary.app.ui.NavigationGraph
import english.dictionary.app.ui.bottomNavigation.BottomNavigation
import english.dictionary.app.ui.theme.EnglishDictionaryTheme
import english.dictionary.app.ui.theme.backgroundColor

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            EnglishDictionaryTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize().background(backgroundColor),
                    bottomBar = { BottomNavigation(navController) }) {
                    NavigationGraph(navController)
                }
            }
        }
    }
}