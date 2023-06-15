package com.task.tmdb.domain.repository

import com.task.tmdb.domain.model.Movie
import com.task.tmdb.domain.model.MovieDetails

interface MovieRepository {
    suspend fun getPopularTVShows(page: Int): List<Movie>
    suspend fun getMovieDetails(movieId: Long): MovieDetails
    suspend fun getSimilarMovies(movieId: Long): List<Movie>
}