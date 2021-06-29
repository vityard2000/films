package com.films.data.network

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.films.domain.FilmsRepository
import com.films.domain.entities.Film

class FilmsPagingSource(private val filmsRepository: FilmsRepository): PagingSource<Int, Film>() {

    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null

        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        val page:Int = params.key ?: 1
        val films = filmsRepository.loadFilms(page)
        val nextPage = if(films.size < 20) null else page + 1
        val prevPage = if(page == 1) null else page - 1

        return LoadResult.Page(films, prevPage, nextPage)
    }
}
