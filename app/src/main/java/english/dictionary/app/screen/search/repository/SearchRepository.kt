package english.dictionary.app.screen.search.repository

import english.dictionary.app.data.Word
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun filter(query: String): List<Word>

    suspend fun addWord(word: Word)
    suspend fun getAllWords(): List<Word>?
    suspend fun updateWord(word: Word): Int
}