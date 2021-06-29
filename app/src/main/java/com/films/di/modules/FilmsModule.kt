package com.films.di.modules

import com.films.data.network.FilmsPagingSource
import com.films.data.network.NetworkFilmsRepositoryService
import com.films.data.repositories.FilmsRepositoryImpl
import com.films.domain.FilmsRepository
import com.films.domain.useCases.LoadFilmsUC
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory

@Module
class FilmsModule {

    @Provides
    fun provideLoadFilmsUC(filmsPagingSource: FilmsPagingSource,coroutineDispatcher: CoroutineDispatcher): LoadFilmsUC {
        return LoadFilmsUC(filmsPagingSource, coroutineDispatcher)
    }

    @Singleton
    @Provides
    fun provideDispatchers(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Singleton
    @Provides
    fun provideFilmsRepository(networkFilmsRepositoryService: NetworkFilmsRepositoryService): FilmsRepository {
        return FilmsRepositoryImpl(networkFilmsRepositoryService)
    }
    @Singleton
    @Provides
    fun provideNetworkFilmsRepositoryService(retrofit: Retrofit): NetworkFilmsRepositoryService {
        return NetworkFilmsRepositoryService(retrofit)
    }

    @Singleton
    @Provides
    fun providePageSource(filmsRepository: FilmsRepository): FilmsPagingSource {
        return FilmsPagingSource(filmsRepository)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        //val httpClient = OkHttpClient.Builder()
        //    .addInterceptor(AuthInterceptor(BuildConfig.FILMS_API_KEY))
        //    .build()
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/movies/v2/reviews/")
            //.client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}