package com.task.tmdb.presentation.movie_details

import com.task.tmdb.domain.model.Movie
import com.task.tmdb.domain.model.MovieDetails

data class MovieDetailsUiState(
    var isLoading: Boolean = false,
    var isSuccess: Boolean = false,
    var errorMessage: String? = null,
    var details: MovieDetails? = null,
    var similarMovies: List<Movie> = emptyList(),
)