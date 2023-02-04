package english.dictionary.app.screen.search.repository.source

import english.dictionary.app.data.Word
import english.dictionary.app.db.WordDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// need data base
class LocalSearchDataSource @Inject constructor(private val wordDao: WordDao) : SearchDataSource {
    override suspend fun filter(query: String): List<Word> {
        return wordDao.filter(query)
    }

    override suspend fun addWord(word: Word) {
        wordDao.insert(word)
    }

    override suspend fun getAllWords(): List<Word>? {
        return wordDao.getAll()
    }

    override suspend fun updateWord(word: Word): Int {
        return wordDao.updateWord(word)
    }
}