package com.films.data.bd

import com.films.domain.interfaces.FilmsRepository
import com.films.domain.entities.Film

class BDFilmsRepositoryService(): FilmsRepository {
    override suspend fun loadFilms(page: Int): List<Film> {
        TODO("Not yet implemented")
    }
}