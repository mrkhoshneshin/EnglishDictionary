package english.dictionary.app.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import english.dictionary.app.screen.home.repository.HomeRepositoryImpl
import english.dictionary.app.screen.home.repository.source.HomeDataSource
import english.dictionary.app.screen.home.repository.source.LocalHomeDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeModule {

    @Binds
    @Singleton
    abstract fun bindHomeRepository(homeRepositoryImpl: HomeRepositoryImpl)

    @Provides
    @Singleton
    fun provideHomeDataSource(): HomeDataSource{
        return LocalHomeDataSource()
    }
}