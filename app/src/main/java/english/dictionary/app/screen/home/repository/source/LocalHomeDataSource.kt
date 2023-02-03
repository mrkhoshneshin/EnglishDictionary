package english.dictionary.app.screen.home.repository.source

import english.dictionary.app.data.User
import english.dictionary.app.data.Word
import english.dictionary.app.db.WordDao
import english.dictionary.app.screen.home.data.Slider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Fetch data locally
 */
class LocalHomeDataSource @Inject constructor(private val dao: WordDao): HomeDataSource {
    override fun getUsersRecentlyJoined(): Flow<List<User>> {
        TODO("Not yet implemented")
    }

    override fun getBanners(): Flow<List<Slider>> {
        TODO("Not yet implemented")
    }

    override fun getFeatures(): Flow<List<String>> {
        TODO("Not yet implemented")
    }

    //TODO should get from db
    override fun getUserName(): Flow<String> {
        return flow { emit("Alireza") }
    }
    override fun getSearchHistory(): Flow<List<Word>> {
        return dao.getSearchedHistory(true)
    }
}