package english.dictionary.app

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import english.dictionary.app.util.AppSettings
import english.dictionary.app.util.dataStore
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val IS_FIRST_ENTER = booleanPreferencesKey("isFirstEnter")

@HiltAndroidApp
class App(): Application(){
}