package english.dictionary.app.screen.profile.repository.source

import english.dictionary.app.data.Book
import english.dictionary.app.data.User
import kotlinx.coroutines.flow.Flow

interface ProfileDataSource {
    fun getUserInformation(): Flow<String>
    fun getEducation(): Flow<String>
    fun getUniversity(): Flow<String>
}