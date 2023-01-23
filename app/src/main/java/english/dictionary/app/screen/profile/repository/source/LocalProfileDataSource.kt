package english.dictionary.app.screen.profile.repository.source

import english.dictionary.app.data.User
import kotlinx.coroutines.flow.Flow

class LocalProfileDataSource: ProfileDataSource {
    override fun getUserInformation(): Flow<User> {
        TODO("Not yet implemented")
    }
}