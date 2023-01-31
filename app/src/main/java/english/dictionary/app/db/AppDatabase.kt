package english.dictionary.app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import english.dictionary.app.data.Word

@Database(entities = [Word::class], version = 1)
abstract class AppDatabase private constructor() : RoomDatabase() {
    abstract fun getWordDao(): WordDao

    companion object {
        private val dbInstance: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            if (dbInstance == null)
                Room.databaseBuilder(context, AppDatabase::class.java, "word_db").build()
            return dbInstance!!
        }
    }
}