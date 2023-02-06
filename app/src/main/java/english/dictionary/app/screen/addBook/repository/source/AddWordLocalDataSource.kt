package english.dictionary.app.screen.addBook.repository.source

import english.dictionary.app.data.Word
import english.dictionary.app.db.WordDao
import javax.inject.Inject

class AddWordLocalDataSource @Inject constructor(private val wordDao: WordDao): AddWordDataSource {
    override suspend fun addWord(word: Word) {
        wordDao.insert(word)
    }
}