package english.dictionary.app.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import english.dictionary.app.screen.search.repository.SearchRepositoryImpl
import english.dictionary.app.screen.search.repository.source.LocalSearchDataSource
import english.dictionary.app.screen.search.repository.source.SearchDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchModule {
    @Binds
    abstract fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl)

    @Provides
    fun provideDataSource(): SearchDataSource{
        return LocalSearchDataSource()
    }
}