package english.dictionary.app

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import english.dictionary.app.ui.NavigationGraph
import english.dictionary.app.ui.bottomNavigation.BottomNavigation
import english.dictionary.app.ui.theme.EnglishDictionaryTheme
import english.dictionary.app.ui.theme.backgroundColor

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (!isGranted)
                Toast.makeText(
                    this,
                    this.getString(R.string.you_can_not_use_mic_feature),
                    Toast.LENGTH_LONG
                ).show()
        }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getDatabaseCount()
        viewModel.databaseCountLiveData().observe(this){
            if (it == 0) {
                viewModel.insertWordsToDatabaseFromJson(this@MainActivity, "vocab.json")
            }
        }
        setContent {
            val navController = rememberNavController()
            var selectedBottomNavigationItemIndex by remember { mutableStateOf(0) }
            EnglishDictionaryTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor),
                    bottomBar = {
                        BottomNavigation(
                            navController,
                            selectedIndex = selectedBottomNavigationItemIndex,
                            onSelectChanged = { selectedBottomNavigationItemIndex = it })
                    }) {
                    NavigationGraph(
                        navController,
                        showShowRuntimePermission_voiceRecord = {
                            requestPermissionLauncher.launch(android.Manifest.permission.RECORD_AUDIO)
                        }, onBackHandledToHome = { selectedBottomNavigationItemIndex = 0 })
                }
            }
        }
    }
}