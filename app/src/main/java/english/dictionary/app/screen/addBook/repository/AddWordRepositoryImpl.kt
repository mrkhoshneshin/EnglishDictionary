package english.dictionary.app.screen.addBook.repository

import english.dictionary.app.data.Word
import english.dictionary.app.screen.addBook.repository.source.AddWordDataSource
import javax.inject.Inject

class AddWordRepositoryImpl @Inject constructor(private val dataSource : AddWordDataSource): AddWordRepository{
    override suspend fun addWord(word: Word) {
        dataSource.addWord(word)
    }
}