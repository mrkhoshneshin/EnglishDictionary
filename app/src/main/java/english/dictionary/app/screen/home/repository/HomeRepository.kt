package english.dictionary.app.screen.home.repository

import english.dictionary.app.data.User
import english.dictionary.app.data.Word
import english.dictionary.app.screen.home.data.Slider
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getUsersRecentlyJoined(): Flow<List<User>>
    fun getBanners(): Flow<List<Slider>>
    //Stuffs at middle of home screen
    fun getFeatures(): Flow<List<String>>

    fun getUserName(): Flow<String>

    fun getSearchHistory() : Flow<List<Word>>
}