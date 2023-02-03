package english.dictionary.app.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "words")
data class Word(

    @ColumnInfo("word_id")
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,

    @ColumnInfo("is_favorite")
    var isFavorite: Boolean = false,

    @ColumnInfo("visited")
    var visited : Boolean = false,

    @ColumnInfo("english_title")
    @SerializedName("word")
    val englishTitle: String? = "",

    @ColumnInfo("persian_title")
    @SerializedName("technical meaning")
    val persianTitle: String? = "",

    @ColumnInfo("desc_en")
    @SerializedName("desc-en")
    val englishDescription: String? = "",

    @ColumnInfo("desc_fa")
    @SerializedName("desc-fa")
    val persianDescription: String? = "",

    @ColumnInfo("picture")
    @SerializedName("Picture")
    val image: String? = "",
)