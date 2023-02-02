package english.dictionary.app.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import english.dictionary.app.data.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM words")
    suspend fun getAll(): List<Word>

    @Query("SELECT * FROM words WHERE english_title LIKE :query || '%'")
    suspend fun filter(query: String): List<Word>
    @Query("SELECT COUNT(*) FROM words")
    suspend fun getCount(): Int
    @Insert
    suspend fun insertAll(vararg words: Word)

    @Query("SELECT * FROM words WHERE is_favorite = :mustFavorite")
    fun getFavorites(mustFavorite: Boolean): Flow<List<Word>>

    @Update
    suspend fun updateWord(word: Word): Int
    @Query("SELECT * FROM words WHERE english_title LIKE :newValue || '%' AND is_favorite = :mustBeFavorite")
    suspend fun filterFavorites(newValue: String, mustBeFavorite: Boolean = true): List<Word>
}