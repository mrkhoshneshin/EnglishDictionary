package english.dictionary.app.screen.word

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import english.dictionary.app.data.Word
import english.dictionary.app.data.WordDetailData
import english.dictionary.app.db.WordDao
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordDetailViewModel @Inject constructor(private val wordDao: WordDao) : ViewModel() {
    fun getUserName(): String {
        return "Alireza"
    }

    fun getWord(): Word {
        return WordDetailData.word
    }

    fun updateWord(word: Word) {
        viewModelScope.launch {
            wordDao.updateWord(word = word)
        }
    }
}