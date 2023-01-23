package english.dictionary.app.screen.search.repository

import english.dictionary.app.data.Word
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun search(query: String): Flow<List<Word>>
}