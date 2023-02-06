package english.dictionary.app.screen.inputInformation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import english.dictionary.app.screen.inputInformation.repository.InputInformationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputInformationViewModel @Inject constructor(private val repository: InputInformationRepository) :
    ViewModel() {
    fun updateUserInformation(fullName: String, education: String, university: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUserInformation(fullName, education, university)
        }
    }
}