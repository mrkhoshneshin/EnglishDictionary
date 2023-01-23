package english.dictionary.app

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import english.dictionary.app.ui.NavigationGraph
import english.dictionary.app.ui.bottomNavigation.BottomNavigation
import english.dictionary.app.ui.theme.EnglishDictionaryTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            EnglishDictionaryTheme {
                Scaffold(bottomBar = { BottomNavigation(navController) }){
                    NavigationGraph(navController)
                }
            }
        }
    }
}