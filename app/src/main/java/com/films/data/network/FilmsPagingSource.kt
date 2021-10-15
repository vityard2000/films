package com.films.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.films.data.network.NetworkFilmsRepositoryService.Companion.toFilm
import com.films.domain.entities.Film
import retrofit2.HttpException

class FilmsPagingSource(
    private val RepositoryService: NetworkFilmsRepositoryService
) : PagingSource<Int, Film>() {

    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null

        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        val page = params.key ?: 1
        val response = RepositoryService.loadFilms(page)
        if (response.isSuccessful){
            val films = checkNotNull(response.body()?.results?.map { filmDto ->  filmDto.toFilm()})
            val nextPage = if(films.size < 20) null else page + 1
            val prevPage = if(page == 1) null else page -1
            return LoadResult.Page(films, prevPage, nextPage)
        } else {
            return LoadResult.Error(HttpException(response))
        }


    }
}
