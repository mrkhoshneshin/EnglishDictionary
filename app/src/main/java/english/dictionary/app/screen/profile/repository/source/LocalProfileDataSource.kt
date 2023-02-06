package english.dictionary.app.screen.profile.repository.source

import android.content.SharedPreferences
import english.dictionary.app.data.UserSettings
import english.dictionary.app.util.educationKey
import english.dictionary.app.util.fullNameKey
import english.dictionary.app.util.universityKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalProfileDataSource @Inject constructor(private val sharedPref: SharedPreferences) :
    ProfileDataSource {
    override fun getUserInformation(): Flow<String> {
        var userName = ""
        var education = ""
        var university = ""
        sharedPref.getString(fullNameKey, "")?.let { userName = it }
        sharedPref.getString(education, "")?.let { education = it }
        sharedPref.getString(university, "")?.let { university = it }

        if (userName.isNotEmpty()) {
            UserSettings.updateEducation(education)
            UserSettings.updateUniversity(university)
            UserSettings.updateFullName(userName)
        }

        return flow { emit(userName) }
    }

    override fun getEducation(): Flow<String> {
        var education = ""
        sharedPref.getString(educationKey, "")?.let { education = it }
        return flow { emit(education) }
    }

    override fun getUniversity(): Flow<String> {
        var university = ""
        sharedPref.getString(universityKey, "")?.let { university = it }
        return flow { emit(university) }
    }
}