package com.films.data.network

import com.films.BuildConfig
import com.films.data.network.model.FilmsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface FilmsService {
    @GET("all.json")
    suspend fun getFilms(
        @Query("offset") offset: Int = 0,
        @Query("order") order: String = "by-publication-date",
        @Query("api-key") apiKey: String = BuildConfig.FILMS_API_KEY
    ): Response<FilmsResponse>

    companion object {
        const val DEFAULT_PAGE_SIZE = 20
        const val MAX_PAGE_SIZE = 20
    }
}