package english.dictionary.app.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import english.dictionary.app.util.JsonHelper
import english.dictionary.app.util.JsonHelperImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun bindJsonHelper(): JsonHelper{
        return JsonHelperImpl()
    }
}