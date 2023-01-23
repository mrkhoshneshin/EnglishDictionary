package english.dictionary.app.screen.profile.repository

import english.dictionary.app.data.User
import english.dictionary.app.screen.profile.repository.source.ProfileDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(dataSource: ProfileDataSource): ProfileRepository {
    override fun getUserInformation(): Flow<User> {
        TODO("Not yet implemented")
    }

}