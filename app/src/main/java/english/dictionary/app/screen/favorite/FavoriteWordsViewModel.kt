package english.dictionary.app.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import english.dictionary.app.data.Word
import english.dictionary.app.db.WordDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteWordsViewModel @Inject constructor(private val wordDao: WordDao) : ViewModel() {

    private val _textFieldValue = MutableStateFlow("")

    val textFieldValue = _textFieldValue.asStateFlow()
    private val _selectedAlphabet = MutableStateFlow("")

    val selectedAlphabet = _selectedAlphabet.asStateFlow()
    private val _favoriteWords = MutableStateFlow(ArrayList<Word>())

    val favoriteWords = _favoriteWords.asStateFlow()

    fun onFavoriteWordsChanged(words: ArrayList<Word>) {
        _favoriteWords.value = words
    }

    init {
        getFavoriteWords()
    }

    fun onSelectedAlphabetChanged(newValue: String) {
        _selectedAlphabet.value = newValue
    }

    fun onTextFieldTextChanged(newValue: String) {
        viewModelScope.launch {
            if (newValue.isEmpty())
                getFavoriteWords()
            else
                _favoriteWords.value = wordDao.filterFavorites(newValue) as ArrayList<Word>
        }
        _textFieldValue.value = newValue
    }

    private fun getFavoriteWords() {
        viewModelScope.launch {
            val favoriteListFlow = wordDao.getFavorites(true)
            favoriteListFlow.collect {
                _favoriteWords.value = it as ArrayList<Word>
            }
        }
    }
}
