package english.dictionary.app

import android.app.Application
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.google.gson.Gson
import dagger.hilt.android.HiltAndroidApp
import english.dictionary.app.data.Word
import english.dictionary.app.data.Words
import java.io.IOException

val IS_FIRST_ENTER = booleanPreferencesKey("isFirstEnter")

@HiltAndroidApp
class App() : Application() {
}