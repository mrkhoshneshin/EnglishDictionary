package english.dictionary.app.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences


val Context.dataStore : DataStore<Preferences> by preferencesDataStore("app_setting")
