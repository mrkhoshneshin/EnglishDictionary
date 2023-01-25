package english.dictionary.app.screen.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import english.dictionary.app.data.User
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {
    fun getUser(): User {
        return User(
            firstName = "Alireza",
            lastName = "Ebrahimian",
            education = "Bachelor of engineering",
            university = "Tehran university"
        )
    }
}