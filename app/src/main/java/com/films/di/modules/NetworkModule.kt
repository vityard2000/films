package com.films.di.modules

import com.films.data.network.NetworkFilmsRepositoryService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
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
    @Singleton
    @Provides
    fun provideNetworkFilmsRepositoryService(retrofit: Retrofit): NetworkFilmsRepositoryService {
        return NetworkFilmsRepositoryService(retrofit)
    }
}