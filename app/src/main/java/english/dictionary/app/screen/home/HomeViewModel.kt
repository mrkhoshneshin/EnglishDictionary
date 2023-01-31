package english.dictionary.app.screen.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import english.dictionary.app.data.Feature
import english.dictionary.app.data.User
import english.dictionary.app.data.Word
import english.dictionary.app.screen.home.data.Slider
import english.dictionary.app.screen.home.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

//TODO add home repository
@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _textFieldValue = MutableStateFlow("")
    val textFieldValue = _textFieldValue.asStateFlow()
    fun getUserName(): Flow<String> {
        return flow { emit("Alireza") }
    }

    fun updateTextFieldValue(newValue: String) {
        _textFieldValue.value = newValue
    }

    fun getFeatures(): List<Feature> {
        return listOf(
            Feature("Collection"),
            Feature("FlashCard"),
            Feature("Grammerly"),
            Feature("Google Translate")
        )
    }

    fun getSliders(): List<Slider> {
        return listOf(Slider(), Slider(), Slider())
    }

    fun getUsers(): List<User> {
        return listOf(
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User()
        )
    }

    fun getHistoryWords(): List<Word> {
        return listOf(
            Word("Abstract", "انتزاعی"),
            Word("Abstract", "انتزاعی"),
            Word("Abstract", "انتزاعی"),
            Word("Abstract", "انتزاعی"),
            Word("Abstract", "انتزاعی")
        )
    }
}