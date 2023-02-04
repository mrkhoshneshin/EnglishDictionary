package english.dictionary.app.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import english.dictionary.app.data.Word
import english.dictionary.app.db.WordDao
import english.dictionary.app.screen.search.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//TODO add repository
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {
    private val _wordsState = MutableStateFlow(ArrayList<Word>())
    val wordState = _wordsState.asStateFlow()

    private val _textFiledState = MutableStateFlow("")
    val textFieldState = _textFiledState.asStateFlow()

    private val _alphabeticItemSelectedState = MutableStateFlow("")
    val alphabeticItemSelectedState = _alphabeticItemSelectedState.asStateFlow()

    private val _theWordSentToDetailScreen = MutableStateFlow(Word())
    val theWordSentToDetailScreen = _theWordSentToDetailScreen.asStateFlow()

    init {
        getAllWords()
    }

    private fun getAllWords() {
        viewModelScope.launch(Dispatchers.IO) {
            _wordsState.value = searchRepository.getAllWords() as ArrayList<Word>
        }
    }

    fun updateAlphabeticValue(newValue: String) {
        _alphabeticItemSelectedState.value = newValue
    }

    fun updateTextFieldValue(newValue: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (newValue.isEmpty()) _wordsState.value = searchRepository.getAllWords() as ArrayList<Word>
            else _wordsState.value = searchRepository.filter(newValue) as ArrayList<Word>
        }
        _textFiledState.value = newValue
    }

    fun getUserName(): String {
        return "Alireza"
    }

    fun onWordsChanged(words: ArrayList<Word>) {
        _wordsState.value = words
    }


    fun onAlphabeticCharItemClicked(char: String) {
    }

    fun onWordItemClicked(word: Word) {
        _theWordSentToDetailScreen.value = word
    }

    fun updateWordForSearchedHistory(word: Word) {
        viewModelScope.launch {
            searchRepository.updateWord(word)
        }
    }

    fun addWord(word: Word){
        viewModelScope.launch {
            searchRepository.addWord(word)
        }
    }
}

