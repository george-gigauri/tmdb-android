package com.task.tmdb.data.repository

import com.task.tmdb.data.mapper.toDomain
import com.task.tmdb.data.remote.service.MovieApi
import com.task.tmdb.domain.model.Movie
import com.task.tmdb.domain.model.MovieDetails
import com.task.tmdb.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {

    override suspend fun getPopularTVShows(page: Int): List<Movie> {
        return movieApi.getPopularTVSeries(page).results.map { it.toDomain() }
    }

    override suspend fun getMovieDetails(movieId: Long): MovieDetails {
        return movieApi.getDetails(movieId).toDomain()
    }

    override suspend fun getSimilarMovies(movieId: Long): List<Movie> {
        return movieApi.getSimilarMovies(movieId).results.map { it.toDomain() }
    }
}