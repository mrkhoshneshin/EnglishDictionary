package english.dictionary.app.screen.word

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import english.dictionary.app.data.Word
import javax.inject.Inject

@HiltViewModel
class WordDetailViewModel @Inject constructor() : ViewModel() {
    fun getUserName(): String {
        return "Alireza"
    }

    fun getWord(): Word {
        return Word(
            englishTitle = "Abrasion Resistance",
            persianTitle = "مقاومت در برابر کنش",
            englishDescription = "Degree of resistance of a material to abrasion or wrear",
            persianDescription = "درجه مقاومت مواد در برابر سایش یا خوردگی"
        )
    }
}