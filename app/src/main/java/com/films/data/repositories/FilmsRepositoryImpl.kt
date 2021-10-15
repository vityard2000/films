package com.films.data.repositories

import com.films.data.bd.BDFilmsRepositoryService
import com.films.data.network.NetworkFilmsRepositoryService
import com.films.domain.interfaces.FilmsRepository
import com.films.domain.entities.Film
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

//
class FilmsRepositoryImpl(
    private val filmRepositoryServiceNetwork: NetworkFilmsRepositoryService,
    private val filmRepositoryServiceBD: BDFilmsRepositoryService,
    private val externalCoroutineScope: CoroutineScope
) : FilmsRepository {
    override suspend fun loadFilms(page: Int): Any {

        val films = externalCoroutineScope.async{
                filmRepositoryServiceNetwork.loadFilms(page)
        }.await()

        return films


    }

}