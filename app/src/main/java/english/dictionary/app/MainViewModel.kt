package english.dictionary.app

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import english.dictionary.app.data.Word
import english.dictionary.app.db.WordDao
import english.dictionary.app.util.JsonHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val jsonHelper: JsonHelper,private val wordDao: WordDao): ViewModel() {

    private val _databaseHasData = MutableStateFlow(value = false)
    val databaseHasData = _databaseHasData.asStateFlow()
    fun insertWordsToDatabaseFromJson(context: Context, fileName : String){
        val jsonString = jsonHelper.getJsonStringFromAsset(context, fileName)
        convertJsonStringToList(jsonString = jsonString)
    }

    private fun convertJsonStringToList(jsonString : String) {
        val words = jsonHelper.convertJsonStringToList(jsonString = jsonString).toTypedArray()
        insertArrayToDatabase(words)
    }

    private fun insertArrayToDatabase(words: Array<Word>){
        viewModelScope.launch(Dispatchers.IO) {
            wordDao.insertAll(words = words)
        }
    }

    fun updateDataStore(dataStore: DataStore<Preferences>, key: Preferences.Key<Boolean>, value : Boolean) {
        viewModelScope.launch {
            dataStore.edit {
                it[key] = value
            }
        }
    }

    fun getFirstWordFromDatabase(){
        viewModelScope.launch {
            if(wordDao.getFirst() != null)
                _databaseHasData.value = true
        }
    }
    fun readDataFromDataStore(dataStore: DataStore<Preferences>, key: Preferences.Key<Boolean>){
        viewModelScope.launch {
            dataStore.data.collectLatest{preferences ->
                if(preferences[key] == null)
                    _databaseHasData.value = false
                else
                    _databaseHasData.value = preferences[key]!!
            }
        }
    }
}