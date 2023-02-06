package english.dictionary.app.screen.inputInformation.repository.sourse

import android.content.SharedPreferences
import english.dictionary.app.data.UserSettings
import english.dictionary.app.util.educationKey
import english.dictionary.app.util.fullNameKey
import english.dictionary.app.util.universityKey
import javax.inject.Inject

class LocalInputUserInformationDataSource @Inject constructor(private val sharedPref: SharedPreferences) :
    InputInformationDataSource {
    override suspend fun updateUserInformation(
        fullName: String,
        education: String,
        university: String
    ) {
        sharedPref.edit().apply {
            putString(fullNameKey, fullName)
            putString(educationKey, education)
            putString(universityKey, university)
        }.apply()
        updateLocally(fullName, education, university)
    }

    private fun updateLocally(fullName: String, education: String, university: String) {
        UserSettings.updateFullName(fullName)
        UserSettings.updateEducation(education)
        UserSettings.updateUniversity(university)
    }
}