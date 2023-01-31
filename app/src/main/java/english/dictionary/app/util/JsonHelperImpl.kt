package english.dictionary.app.util

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import english.dictionary.app.data.Word
import english.dictionary.app.data.Words
import java.io.IOException

class JsonHelperImpl(private val gson: Gson = Gson()) : JsonHelper{
    private val TAG = "JsonHelperImpl"

    override fun getJsonStringFromAsset(context: Context): String {
        var jsonString = ""
        try {
            val inputStream = context.assets.open("vocab.json")
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
        Log.i(TAG, "convertJsonStringToList: ${Words.words}")
        return list
    }

}