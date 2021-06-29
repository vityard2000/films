package com.films.ui.screens.listFilmsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.films.domain.entities.Film
import com.films.domain.useCases.LoadFilmsUC
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext

// viewModel экрана со списком фильмов
class ListFilmsViewModel(private val loadFilmsUC: LoadFilmsUC): ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _page = MutableStateFlow(1)
    val page: StateFlow<Int> = _page.asStateFlow()

    @ExperimentalCoroutinesApi
    val films: StateFlow<PagingData<Film>> = page
        .map(::newPager)
        .flatMapLatest { pager -> pager.flow }
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private suspend fun newPager(page: Int): Pager<Int, Film> {
        _isLoading.value = true
        val pager = loadFilmsUC.loadFilms()
        _isLoading.value = false
        return pager
    }
}