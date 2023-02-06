package english.dictionary.app.screen.profile.repository

import english.dictionary.app.data.User
import english.dictionary.app.screen.profile.repository.source.ProfileDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private val dataSource: ProfileDataSource): ProfileRepository {
    override fun getUserInformation(): Flow<String> {
        return dataSource.getUserInformation()
    }

    override fun getEducation(): Flow<String> {
        return dataSource.getEducation()
    }

    override fun getUniversity(): Flow<String> {
        return dataSource.getUniversity()
    }

}