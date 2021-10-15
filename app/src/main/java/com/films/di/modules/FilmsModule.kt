package com.films.di.modules

import com.films.data.bd.BDFilmsRepositoryService
import com.films.data.network.NetworkFilmsRepositoryService
import com.films.data.repositories.FilmsRepositoryImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
class FilmsModule {

    @Singleton
    @Provides
    fun provideFilmsRepositoryImpl(
        networkFilmsRepositoryService: NetworkFilmsRepositoryService,
        bdFilmsRepositoryService: BDFilmsRepositoryService,
        coroutineScope: CoroutineScope
    ): FilmsRepositoryImpl {
        return FilmsRepositoryImpl(networkFilmsRepositoryService, bdFilmsRepositoryService, coroutineScope)
    }

}