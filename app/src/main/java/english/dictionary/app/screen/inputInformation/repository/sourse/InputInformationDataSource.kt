package english.dictionary.app.screen.inputInformation.repository.sourse

interface InputInformationDataSource {
    suspend fun updateUserInformation(fullName : String, education : String, university: String)
}