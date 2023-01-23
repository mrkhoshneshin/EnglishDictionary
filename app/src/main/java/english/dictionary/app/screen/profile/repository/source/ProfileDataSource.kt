package english.dictionary.app.screen.profile.repository.source

import english.dictionary.app.data.User
import kotlinx.coroutines.flow.Flow

interface ProfileDataSource {
    fun getUserInformation(): Flow<User>
}