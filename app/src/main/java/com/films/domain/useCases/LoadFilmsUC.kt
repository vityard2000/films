package com.films.domain.useCases

import com.films.domain.interfaces.FilmsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

//данный usecase отвечает за загрузку фильмов


class LoadFilmsUC(private val filmsRepository: FilmsRepository) {

    suspend fun loadFilms(page: Int) = withContext(Dispatchers.IO) {
        filmsRepository.loadFilms(page)
    }

}