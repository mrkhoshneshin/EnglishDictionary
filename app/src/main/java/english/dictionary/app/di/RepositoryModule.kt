package english.dictionary.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import english.dictionary.app.screen.home.repository.HomeRepository
import english.dictionary.app.screen.home.repository.HomeRepositoryImpl
import english.dictionary.app.screen.home.repository.source.HomeDataSource
import english.dictionary.app.screen.home.repository.source.LocalHomeDataSource
import english.dictionary.app.screen.inputInformation.repository.InputInformationRepository
import english.dictionary.app.screen.inputInformation.repository.InputInformationRepositoryImpl
import english.dictionary.app.screen.inputInformation.repository.sourse.InputInformationDataSource
import english.dictionary.app.screen.inputInformation.repository.sourse.LocalInputUserInformationDataSource
import english.dictionary.app.screen.profile.repository.ProfileRepository
import english.dictionary.app.screen.profile.repository.ProfileRepositoryImpl
import english.dictionary.app.screen.profile.repository.source.LocalProfileDataSource
import english.dictionary.app.screen.profile.repository.source.ProfileDataSource
import english.dictionary.app.screen.search.repository.SearchRepository
import english.dictionary.app.screen.search.repository.SearchRepositoryImpl
import english.dictionary.app.screen.search.repository.source.LocalSearchDataSource
import english.dictionary.app.screen.search.repository.source.SearchDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    @Singleton
    abstract fun bindHomeDataSource(homeDataSource: LocalHomeDataSource): HomeDataSource

    @Binds
    @Singleton
    abstract fun bindProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    @Singleton
    abstract fun bindProfileDataSource(dataSource: LocalProfileDataSource): ProfileDataSource

    @Binds
    @Singleton
    abstract fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository

    @Binds
    @Singleton
    abstract fun bindSearchDataSource(dataSource: LocalSearchDataSource): SearchDataSource

    @Binds
    @Singleton
    abstract fun bindInputInformationRepository(impl: InputInformationRepositoryImpl): InputInformationRepository

    @Binds
    @Singleton
    abstract fun bindInputInformationDataSource(dataSource: LocalInputUserInformationDataSource): InputInformationDataSource
}