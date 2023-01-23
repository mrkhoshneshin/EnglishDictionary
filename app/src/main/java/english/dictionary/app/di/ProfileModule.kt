package english.dictionary.app.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import english.dictionary.app.screen.home.repository.HomeRepository
import english.dictionary.app.screen.home.repository.HomeRepositoryImpl
import english.dictionary.app.screen.home.repository.source.HomeDataSource
import english.dictionary.app.screen.profile.repository.ProfileRepository
import english.dictionary.app.screen.profile.repository.ProfileRepositoryImpl
import english.dictionary.app.screen.profile.repository.source.LocalProfileDataSource
import english.dictionary.app.screen.profile.repository.source.ProfileDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProfileModule {

    @Provides
    @Singleton
    @Inject
    fun provideProfileRepository(dataSource: ProfileDataSource): ProfileRepository {
        return ProfileRepositoryImpl(dataSource)
    }

    @Provides
    fun provideDataSource(): ProfileDataSource {
        return LocalProfileDataSource()
    }
}