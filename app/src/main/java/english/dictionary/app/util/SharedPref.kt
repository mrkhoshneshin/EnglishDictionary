package english.dictionary.app.util

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey

const val fullNameKey = "fullName"
const val educationKey = "education"
const val universityKey = "university"
object SharedPref{

    lateinit var sharedPref: SharedPreferences
    fun initialize(context: Context){
        sharedPref = context.getSharedPreferences("userSettings",Context.MODE_PRIVATE)
    }
}
