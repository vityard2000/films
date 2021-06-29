package com.films.domain.useCases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.films.data.network.FilmsPagingSource
import com.films.domain.entities.Film
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

//данный use case отвечает за загрузку фильмов

class LoadFilmsUC(private val pagingSource: FilmsPagingSource,
                  private val dispatcher: CoroutineDispatcher) {

    suspend fun loadFilms():
            Pager<Int, Film> {
        return withContext(dispatcher){
            Pager<Int,Film>(
                PagingConfig(pageSize = 20)
            ){
                pagingSource
            }
        }
    }
}