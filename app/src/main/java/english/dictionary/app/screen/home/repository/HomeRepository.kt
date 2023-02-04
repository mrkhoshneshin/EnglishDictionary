package english.dictionary.app.screen.home.repository

import english.dictionary.app.data.Book
import english.dictionary.app.data.User
import english.dictionary.app.data.Word
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getUsersRecentlyJoined(): Flow<List<User>>
    fun getBanners(): Flow<List<Book>>
    //Stuffs at middle of home screen
    fun getFeatures(): Flow<List<String>>

    fun getUserName(): Flow<String>

    fun getSearchHistory() : Flow<List<Word>>
}