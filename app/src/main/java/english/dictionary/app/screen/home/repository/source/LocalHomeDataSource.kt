package english.dictionary.app.screen.home.repository.source

import android.content.SharedPreferences
import english.dictionary.app.data.Book
import english.dictionary.app.data.User
import english.dictionary.app.data.UserSettings
import english.dictionary.app.data.Word
import english.dictionary.app.db.WordDao
import english.dictionary.app.util.educationKey
import english.dictionary.app.util.fullNameKey
import english.dictionary.app.util.universityKey
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Fetch data locally
 */
class LocalHomeDataSource @Inject constructor(
    private val dao: WordDao,
    private val sharedPref: SharedPreferences
) : HomeDataSource {
    override fun getUsersRecentlyJoined(): Flow<List<User>> {
        TODO("Not yet implemented")
    }

    override fun getBanners(): Flow<List<Book>> {
        TODO("Not yet implemented")
    }

    override fun getFeatures(): Flow<List<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserInformation() {
        sharedPref.getString(fullNameKey, "")?.let { UserSettings.updateFullName(it) }
        sharedPref.getString(educationKey, "")?.let { UserSettings.updateEducation(it) }
        sharedPref.getString(universityKey, "")?.let { UserSettings.updateUniversity(it) }
    }

    override fun getSearchHistory(): Flow<List<Word>> {
        return dao.getSearchedHistory(true)
    }

    override suspend fun getUserName(): String {
        sharedPref.getString(fullNameKey, "")?.let {
            updateUserInformation()
            return it
        }
        return ""
    }
}