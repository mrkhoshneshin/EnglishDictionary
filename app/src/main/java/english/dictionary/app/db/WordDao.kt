package english.dictionary.app.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import english.dictionary.app.data.Word
import english.dictionary.app.data.Words

@Dao
interface WordDao {
    @Query("SELECT * FROM word")
    fun getAll(): List<Word>

    @Query("SELECT * FROM word WHERE english_title LIKE :query")
    fun find(query: String): List<Word>

    @Insert
    fun insertAll(vararg words: Word)
}