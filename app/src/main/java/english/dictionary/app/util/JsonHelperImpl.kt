package english.dictionary.app.util

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import english.dictionary.app.data.Word
import java.io.IOException
import javax.inject.Inject

class JsonHelperImpl (private val gson: Gson = Gson()) : JsonHelper {
    private val TAG = "JsonHelperImpl"

    override fun getJsonStringFromAsset(context: Context, fileName: String): String {
        var jsonString = ""
        try {
            val inputStream = context.assets.open(fileName)
            inputStream.bufferedReader().use {
                jsonString = it.readText()
            }
        } catch (e: IOException) {
            Log.e(TAG, "getJsonString: $e")
        }
        return jsonString
    }

    override fun convertJsonStringToList(jsonString: String): List<Word> {
        val list = gson.fromJson(jsonString, Array<Word>::class.java).asList()
        return list
    }

}