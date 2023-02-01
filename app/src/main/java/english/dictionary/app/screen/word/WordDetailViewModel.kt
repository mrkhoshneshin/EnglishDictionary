package english.dictionary.app.screen.word

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import english.dictionary.app.data.Word
import english.dictionary.app.data.WordDetailData
import javax.inject.Inject

@HiltViewModel
class WordDetailViewModel @Inject constructor() : ViewModel() {
    fun getUserName(): String {
        return "Alireza"
    }

    fun getWord(): Word {
        return WordDetailData.word
    }
}