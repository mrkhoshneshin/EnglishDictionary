package english.dictionary.app.data

import com.google.gson.annotations.SerializedName

data class Word(
    @SerializedName("word")
    val englishTitle: String = "",
    @SerializedName("technical meaning")
    val persianTitle: String = "",
    @SerializedName("desc-en")
    val englishDescription: String = "",
    @SerializedName("desc-fa")
    val persianDescription: String = "",
    @SerializedName("Picture")
    val image: String = "",
)