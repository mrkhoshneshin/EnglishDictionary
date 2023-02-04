package english.dictionary.app.screen.book

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import english.dictionary.app.R
import english.dictionary.app.data.Book
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(): ViewModel(){
    fun getBooks() : List<Book> = books()
}

