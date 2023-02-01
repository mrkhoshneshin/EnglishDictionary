package english.dictionary.app.util

import android.content.Context
import english.dictionary.app.data.Word

interface JsonHelper {

    fun getJsonStringFromAsset(context: Context, fileName: String): String
    fun convertJsonStringToList(jsonString: String): List<Word>
}