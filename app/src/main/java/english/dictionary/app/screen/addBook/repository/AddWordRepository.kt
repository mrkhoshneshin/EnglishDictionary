package english.dictionary.app.screen.addBook.repository

import english.dictionary.app.data.Word

interface AddWordRepository {
    suspend fun addWord(word: Word)
}