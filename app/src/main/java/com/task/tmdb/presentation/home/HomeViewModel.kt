package com.task.tmdb.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.task.tmdb.data.remote.paging.PopularTVShowsPagingSource
import com.task.tmdb.domain.model.Movie
import com.task.tmdb.domain.use_case.GetPopularTVShowsPagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularTVShowsPagingUseCase: GetPopularTVShowsPagingUseCase,
    private val popularTVShowsPagingSource: PopularTVShowsPagingSource
) : ViewModel() {

    val data: Flow<PagingData<Movie>> = getPopularTVShowsPagingUseCase().cachedIn(viewModelScope)
}