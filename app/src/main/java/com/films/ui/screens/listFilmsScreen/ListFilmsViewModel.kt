package com.films.ui.screens.listFilmsScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.films.data.network.FilmsPagingSource
import com.films.data.network.NetworkFilmsRepositoryService
import com.films.domain.entities.Film
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

// viewModel экрана со списком фильмов
class ListFilmsViewModel(private val repository: NetworkFilmsRepositoryService) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _page = MutableStateFlow(1)
    val page: StateFlow<Int> = _page.asStateFlow()
    private val source = FilmsPagingSource(repository)

    val config = PagingConfig(
        pageSize = 20,
        prefetchDistance = 20,
        enablePlaceholders = true,
        initialLoadSize = 40,
        maxSize = 60,
        //jumpThreshold = 20
    )

    val films: StateFlow<PagingData<Film>> = Pager(config) {
        source
    }.flow.cachedIn(viewModelScope).stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}