package english.dictionary.app.screen.search

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import english.dictionary.app.data.Word
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


//TODO add repository
@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {
    private val _wordsState = MutableStateFlow(mutableListOf<Word>())
    val wordState = _wordsState.asStateFlow()

    init {
        _wordsState.value = mutableListOf(
            Word(englishDescription = "Hello"),
            Word(englishDescription = "Hello"),
            Word(englishDescription = "Hello"),
            Word(englishDescription = "Hello"),
            Word(englishDescription = "Hello"),
            Word(englishDescription = "Hello"),
            Word(englishDescription = "Hello"),
            Word(englishDescription = "Hello"),
            Word(englishDescription = "Hello"),
            Word(englishDescription = "Hello"),
            Word(englishDescription = "Hello"),
            Word(englishDescription = "Hello"),
            Word(englishDescription = "Hello"),
            Word(englishDescription = "Hello"),
            Word(englishDescription = "Hello"),
            Word(englishDescription = "Hello"),
        )
    }

    fun getUserName(): String {
        return "Alireza"
    }
    fun onWordsChanged(words: MutableList<Word>) {
        _wordsState.value = words
    }

    fun onTextFieldTextChanged(text: String) {
    }

    fun onAlphabeticCharItemClicked(char: String) {
    }
}