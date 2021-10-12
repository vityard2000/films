package com.films.data.repositories

import com.films.data.bd.BDFilmsRepositoryService
import com.films.data.network.NetworkFilmsRepositoryService
import com.films.domain.interfaces.FilmsRepository
import com.films.domain.entities.Film

//
class FilmsRepositoryImpl(
    private val filmRepositoryServiceNetwork: NetworkFilmsRepositoryService,
    private val filmRepositoryServiceBD: BDFilmsRepositoryService,
) : FilmsRepository {
    override suspend fun loadFilms(page: Int): List<Film> {
        //TODO Добавить проверку на наличие соединения
        if (true)
            return filmRepositoryServiceNetwork.loadFilms(page)
        else
            return filmRepositoryServiceBD.loadFilms(page)
    }

}