package english.dictionary.app.screen.home.repository

import english.dictionary.app.data.Book
import english.dictionary.app.data.User
import english.dictionary.app.data.Word
import english.dictionary.app.screen.home.repository.source.HomeDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val dataSource: HomeDataSource) : HomeRepository {
    override fun getUsersRecentlyJoined(): Flow<List<User>> {
        TODO("Not yet implemented")
    }

    override fun getBanners(): Flow<List<Book>> {
        TODO("Not yet implemented")
    }

    override fun getFeatures(): Flow<List<String>> {
        TODO("Not yet implemented")
    }

    override fun getSearchHistory(): Flow<List<Word>> {
        return dataSource.getSearchHistory()
    }

    override suspend fun getUserName(): String {
        return dataSource.getUserName()
    }

}