package english.dictionary.app.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import english.dictionary.app.screen.home.repository.HomeRepository
import english.dictionary.app.screen.home.repository.HomeRepositoryImpl
import english.dictionary.app.screen.home.repository.source.HomeDataSource
import english.dictionary.app.screen.home.repository.source.LocalHomeDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeModule {
    @Provides
    @Singleton
    @Inject
    fun provideHomeRepository(dataSource: HomeDataSource): HomeRepository {
        return HomeRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideHomeDataSource(): HomeDataSource {
        return LocalHomeDataSource()
    }
}