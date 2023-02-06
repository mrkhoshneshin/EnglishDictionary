package english.dictionary.app.di

import android.content.SharedPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import english.dictionary.app.util.JsonHelper
import english.dictionary.app.util.JsonHelperImpl
import english.dictionary.app.util.SharedPref
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun bindJsonHelper(): JsonHelper{
        return JsonHelperImpl()
    }

    @Singleton
    @Provides
    fun provideSharedPref(): SharedPreferences{
        return SharedPref.sharedPref
    }
}