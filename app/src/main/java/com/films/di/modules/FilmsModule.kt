package com.films.di.modules

import com.films.data.bd.BDFilmsRepositoryService
import com.films.data.network.FilmsPagingSource
import com.films.data.network.NetworkFilmsRepositoryService
import com.films.data.repositories.FilmsRepositoryImpl
import com.films.domain.interfaces.FilmsRepository
import com.films.domain.useCases.LoadFilmsUC
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class FilmsModule {

    @Provides
    fun provideLoadFilmsUC(filmsRepository: FilmsRepositoryImpl): LoadFilmsUC {
        return LoadFilmsUC(filmsRepository)
    }

    @Singleton
    @Provides
    fun provideFilmsRepositoryImpl(networkFilmsRepositoryService: NetworkFilmsRepositoryService, bdFilmsRepositoryService: BDFilmsRepositoryService): FilmsRepositoryImpl {
        return FilmsRepositoryImpl(networkFilmsRepositoryService, bdFilmsRepositoryService)
    }
    @Singleton
    @Provides
    fun provideNetworkFilmsRepositoryService(retrofit: Retrofit): NetworkFilmsRepositoryService {
        return NetworkFilmsRepositoryService(retrofit)
    }

    @Singleton
    @Provides
    fun provideBDFilmsRepository(): BDFilmsRepositoryService {
        return BDFilmsRepositoryService()
    }



}