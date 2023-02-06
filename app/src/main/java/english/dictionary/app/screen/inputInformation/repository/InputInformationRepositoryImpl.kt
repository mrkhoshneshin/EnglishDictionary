package english.dictionary.app.screen.inputInformation.repository

import english.dictionary.app.screen.inputInformation.repository.sourse.InputInformationDataSource
import javax.inject.Inject

class InputInformationRepositoryImpl @Inject constructor(private val localDataSource: InputInformationDataSource) :
    InputInformationRepository {
    override suspend fun updateUserInformation(
        fullName: String,
        education: String,
        university: String
    ) {
        localDataSource.updateUserInformation(fullName, education, university)
    }
}