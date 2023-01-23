package english.dictionary.app.screen.search.repository.source

import english.dictionary.app.data.Word
import kotlinx.coroutines.flow.Flow

interface SearchDataSource {
    fun search(query: String): Flow<List<Word>>
}