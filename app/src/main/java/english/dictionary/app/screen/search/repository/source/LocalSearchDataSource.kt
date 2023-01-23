package english.dictionary.app.screen.search.repository.source

import english.dictionary.app.data.Word
import kotlinx.coroutines.flow.Flow

// need data base
class LocalSearchDataSource: SearchDataSource {
    override fun search(query: String): Flow<List<Word>> {
        TODO("Not yet implemented")
    }
}