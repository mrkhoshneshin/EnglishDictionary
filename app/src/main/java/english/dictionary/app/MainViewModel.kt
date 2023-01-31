package english.dictionary.app

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import english.dictionary.app.data.Word
import english.dictionary.app.util.JsonHelperImpl
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    private val jsonHelper = JsonHelperImpl()


    fun getJsonStringFromAsset(context: Context): String{
        return jsonHelper.getJsonStringFromAsset(context)
    }

    fun convertJsonStringToList(jsonString : String): List<Word>{
        return jsonHelper.convertJsonStringToList(jsonString = jsonString)
    }
}