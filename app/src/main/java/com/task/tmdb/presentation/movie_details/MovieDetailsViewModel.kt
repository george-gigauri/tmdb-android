package com.task.tmdb.presentation.movie_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.tmdb.common.Const
import com.task.tmdb.common.Result
import com.task.tmdb.domain.model.Movie
import com.task.tmdb.domain.model.MovieDetails
import com.task.tmdb.domain.use_case.GetMovieDetailsUseCase
import com.task.tmdb.domain.use_case.GetSimilarMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _details: MutableStateFlow<Result<MovieDetails>> = MutableStateFlow(Result.Empty)
    val details: StateFlow<Result<MovieDetails>> = _details.asStateFlow()

    private val _similar: MutableStateFlow<Result<List<Movie>>> = MutableStateFlow(Result.Empty)
    val similar: StateFlow<Result<List<Movie>>> = _similar.asStateFlow()

    private val movieId: Int get() = savedStateHandle[Const.PARAM_MOVIE_ID] ?: -1

    init {
        getDetails()
        getSimilarMovies()
    }

    private fun getDetails() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            _details.value = Result.Loading
            val result = getMovieDetailsUseCase(movieId.toLong())
            _details.value = result
        }
    }

    private fun getSimilarMovies() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            _similar.value = Result.Loading
            val result = getSimilarMoviesUseCase(movieId.toLong())
            _similar.value = result
        }
    }
}