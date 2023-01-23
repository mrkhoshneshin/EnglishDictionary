package english.dictionary.app.screen.home.repository.source

import english.dictionary.app.data.User
import english.dictionary.app.screen.home.data.Slider
import kotlinx.coroutines.flow.Flow

/**
 * Fetch data locally
 */
class LocalHomeDataSource: HomeDataSource {
    override fun getUsersRecentlyJoined(): Flow<List<User>> {
        TODO("Not yet implemented")
    }

    override fun getBanners(): Flow<List<Slider>> {
        TODO("Not yet implemented")
    }

    override fun getFeatures(): Flow<List<String>> {
        TODO("Not yet implemented")
    }
}