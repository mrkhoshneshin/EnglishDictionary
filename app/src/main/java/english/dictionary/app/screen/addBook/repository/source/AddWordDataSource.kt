package english.dictionary.app.screen.addBook.repository.source

import english.dictionary.app.data.Word

interface AddWordDataSource {
    suspend fun addWord(word: Word)
}