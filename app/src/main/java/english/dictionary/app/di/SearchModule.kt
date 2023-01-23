package english.dictionary.app.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import english.dictionary.app.screen.home.repository.HomeRepository
import english.dictionary.app.screen.home.repository.HomeRepositoryImpl
import english.dictionary.app.screen.home.repository.source.HomeDataSource
import english.dictionary.app.screen.search.repository.SearchRepository
import english.dictionary.app.screen.search.repository.SearchRepositoryImpl
import english.dictionary.app.screen.search.repository.source.LocalSearchDataSource
import english.dictionary.app.screen.search.repository.source.SearchDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SearchModule {
    @Provides
    @Singleton
    @Inject
    fun provideSearchRepository(dataSource: SearchDataSource): SearchRepository {
        return SearchRepositoryImpl(dataSource)
    }
    @Provides
    fun provideDataSource(): SearchDataSource {
        return LocalSearchDataSource()
    }
}