package english.dictionary.app.screen.search.repository

import english.dictionary.app.data.Word
import english.dictionary.app.screen.search.repository.source.SearchDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val dataSource: SearchDataSource): SearchRepository {
    override suspend fun filter(query: String): List<Word> {
        return dataSource.filter(query)
    }

    override suspend fun addWord(word: Word) {
        dataSource.addWord(word)
    }

    override suspend fun getAllWords(): List<Word>? {
        return dataSource.getAllWords()
    }

    override suspend fun updateWord(word: Word): Int {
        return dataSource.updateWord(word)
    }
}