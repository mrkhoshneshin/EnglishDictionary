package english.dictionary.app.screen.inputInformation.repository

interface InputInformationRepository {
    suspend fun updateUserInformation(fullName : String, education : String, university: String)
}