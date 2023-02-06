package english.dictionary.app.screen.profile.repository

import english.dictionary.app.data.User
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getUserInformation(): Flow<String>
    fun getEducation(): Flow<String>
    fun getUniversity(): Flow<String>
}