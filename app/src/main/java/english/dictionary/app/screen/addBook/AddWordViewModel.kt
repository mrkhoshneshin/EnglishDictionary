package english.dictionary.app.screen.addBook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import english.dictionary.app.data.Word
import english.dictionary.app.screen.addBook.repository.AddWordRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddWordViewModel @Inject constructor(private val repository: AddWordRepository): ViewModel() {
    fun addWord(word: Word){
        viewModelScope.launch {
            repository.addWord(word = word)
        }
    }
}