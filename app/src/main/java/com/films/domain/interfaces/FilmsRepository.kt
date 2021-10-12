package com.films.domain.interfaces

import com.films.domain.entities.Film


interface FilmsRepository {
    suspend fun loadFilms(page: Int): List<Film>
}