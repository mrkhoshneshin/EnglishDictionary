package english.dictionary.app.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


object UserSettings {

    private val _fullNameState = MutableStateFlow("")
    val fullNameState = _fullNameState.asStateFlow()

    private val _educationState = MutableStateFlow("")
    val educationState = _educationState.asStateFlow()

    private val _universityState = MutableStateFlow("")
    val universityState = _universityState.asStateFlow()

    fun updateFullName(fullName: String) {
        _fullNameState.value = fullName
    }

    fun updateEducation(education: String) {
        _educationState.value = education
    }

    fun updateUniversity(university: String) {
        _universityState.value = university
    }
}