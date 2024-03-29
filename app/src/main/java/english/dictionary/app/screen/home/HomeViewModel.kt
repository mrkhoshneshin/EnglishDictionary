package english.dictionary.app.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import english.dictionary.app.data.Book
import english.dictionary.app.data.Feature
import english.dictionary.app.data.Word
import english.dictionary.app.screen.book.books
import english.dictionary.app.screen.home.data.FeatureEnum
import english.dictionary.app.screen.home.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

//TODO add home repository
@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    private val _textFieldValue = MutableStateFlow("")
    val textFieldValue = _textFieldValue.asStateFlow()

    private val _searchedHistoryWords = MutableStateFlow(ArrayList<Word>())
    val searchedHistoryWords = _searchedHistoryWords.asStateFlow()

    private val _userNameState = MutableStateFlow("")
    val userNameState = _userNameState.asStateFlow()

    init {
        getUserName()
        getHistoryWords()
    }

    private fun getUserName() {
        viewModelScope.launch(Dispatchers.IO) {
            _userNameState.value = homeRepository.getUserName()
        }
    }

    fun updateTextFieldValue(newValue: String) {
        _textFieldValue.value = newValue
    }

    fun getFeatures(): List<Feature> {
        return listOf(
            Feature(id = FeatureEnum.FLASH_CARD, "FlashCard"),
            Feature(id = FeatureEnum.COLLECTION, "Collection"),
            Feature(id = FeatureEnum.GRAMMARLY, "Grammerly"),
            Feature(id = FeatureEnum.GOOGLE_TRANSLATE, "Google Translate"),
        )
    }

    fun getSliders(): List<Book> {
        return books()
    }

    fun getHistoryWords() {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.getSearchHistory().collectLatest {
                _searchedHistoryWords.value = it as ArrayList<Word>
            }
        }
    }

    fun getGoogleTranslateUrl(): String {
        return "https://translate.google.com/"
    }

    fun getGrammerlyUrl(): String {
        return "https://app.grammarly.com/"
    }
}