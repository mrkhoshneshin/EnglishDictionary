package english.dictionary.app.screen.input_information

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import english.dictionary.app.IS_FIRST_ENTER
import english.dictionary.app.util.dataStore
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputUserInformationViewModel @Inject constructor(): ViewModel() {
    fun updateFirstEnterValue(context: Context, value : Boolean){
        viewModelScope.launch {
            context.dataStore.edit {
                it[IS_FIRST_ENTER] = value
            }
        }
    }
}