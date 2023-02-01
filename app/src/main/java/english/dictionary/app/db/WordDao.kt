package english.dictionary.app.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import english.dictionary.app.data.Word

@Dao
interface WordDao {
    @Query("SELECT * FROM words")
    suspend fun getAll(): List<Word>

    @Query("SELECT * FROM words WHERE english_title LIKE :query || '%'")
    suspend fun filter(query: String): List<Word>

    @Insert
    suspend fun insertAll(vararg words: Word)
}