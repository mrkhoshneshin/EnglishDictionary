package english.dictionary.app

import android.app.Application
import androidx.datastore.preferences.core.booleanPreferencesKey
import dagger.hilt.android.HiltAndroidApp
import english.dictionary.app.db.AppDatabase
import english.dictionary.app.util.SharedPref

val IS_FIRST_ENTER = booleanPreferencesKey("isFirstEnter")
val DATA_BASE_HAS_DATA = booleanPreferencesKey("database_has_data")
@HiltAndroidApp
class App() : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPref.initialize(this)
    }
}