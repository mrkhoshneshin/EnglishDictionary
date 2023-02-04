package english.dictionary.app.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import english.dictionary.app.data.Feature
import english.dictionary.app.data.Word
import english.dictionary.app.screen.home.data.FeatureEnum
import english.dictionary.app.screen.home.data.Slider
import english.dictionary.app.screen.home.repository.HomeRepository
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

    init {
        getHistoryWords()
    }
    fun getUserName(): Flow<String> {
        return flow { emit("Alireza") }
    }

    fun updateTextFieldValue(newValue: String) {
        _textFieldValue.value = newValue
    }

    fun getFeatures(): List<Feature> {
        return listOf(
            Feature(id = FeatureEnum.COLLECTION,"Collection"),
            Feature(id = FeatureEnum.FLASH_CARD,"FlashCard"),
            Feature(id = FeatureEnum.GRAMMARLY,"Grammerly"),
            Feature(id = FeatureEnum.GOOGLE_TRANSLATE,"Google Translate")
        )
    }

    fun getSliders(): List<Slider> {
        return listOf(Slider(), Slider(), Slider())
    }

    fun getHistoryWords() {
        viewModelScope.launch {
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