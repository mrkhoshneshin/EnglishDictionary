package english.dictionary.app.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import english.dictionary.app.screen.profile.repository.ProfileRepositoryImpl
import english.dictionary.app.screen.profile.repository.source.LocalProfileDataSource
import english.dictionary.app.screen.profile.repository.source.ProfileDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileModule {
    @Binds
    abstract fun bindProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl)

    @Provides
    fun provideDataSource(): ProfileDataSource{
        return LocalProfileDataSource()
    }
}