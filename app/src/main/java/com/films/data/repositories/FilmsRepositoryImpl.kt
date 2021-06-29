package com.films.data.repositories

import com.films.data.network.NetworkFilmsRepositoryService
import com.films.domain.FilmsRepository

//
class FilmsRepositoryImpl(private val filmRepositoryServiceNetwork: NetworkFilmsRepositoryService): FilmsRepository {

    override suspend fun loadFilms(page: Int) = filmRepositoryServiceNetwork.loadFilms(page)

}