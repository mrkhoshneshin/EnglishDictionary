package english.dictionary.app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import english.dictionary.app.db.AppDatabase
import english.dictionary.app.db.WordDao
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    @Inject
    fun provideWordDao(@ApplicationContext context: Context): WordDao{
        return AppDatabase.getDatabase(context).getWordDao()
    }
}