package english.dictionary.app.screen.home.repository

import english.dictionary.app.data.User
import english.dictionary.app.data.Word
import english.dictionary.app.screen.home.data.Slider
import english.dictionary.app.screen.home.repository.source.HomeDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val dataSource: HomeDataSource) : HomeRepository {
    override fun getUsersRecentlyJoined(): Flow<List<User>> {
        TODO("Not yet implemented")
    }

    override fun getBanners(): Flow<List<Slider>> {
        TODO("Not yet implemented")
    }

    override fun getFeatures(): Flow<List<String>> {
        TODO("Not yet implemented")
    }

    override fun getUserName(): Flow<String> {
       return dataSource.getUserName()
    }

    override fun getSearchHistory(): Flow<List<Word>> {
        return dataSource.getSearchHistory()
    }

}