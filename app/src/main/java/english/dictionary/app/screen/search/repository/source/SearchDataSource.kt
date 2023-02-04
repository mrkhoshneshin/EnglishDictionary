package english.dictionary.app.screen.search.repository.source

import english.dictionary.app.data.Word
import kotlinx.coroutines.flow.Flow

interface SearchDataSource {
    suspend fun filter(query: String): List<Word>

    suspend fun addWord(word: Word)
    suspend fun getAllWords(): List<Word>?
    suspend fun updateWord(word: Word): Int
}