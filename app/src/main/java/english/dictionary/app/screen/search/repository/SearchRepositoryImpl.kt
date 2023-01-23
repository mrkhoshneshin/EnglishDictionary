package english.dictionary.app.screen.search.repository

import english.dictionary.app.data.Word
import english.dictionary.app.screen.search.repository.source.SearchDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(dataSource: SearchDataSource): SearchRepository {
    override fun search(query: String): Flow<List<Word>> {
        TODO("Not yet implemented")
    }
}