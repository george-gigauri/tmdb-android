package com.task.tmdb.domain.use_case

import com.task.tmdb.common.Result
import com.task.tmdb.domain.model.Movie
import com.task.tmdb.domain.repository.MovieRepository
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetSimilarMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(movieId: Long): Result<List<Movie>> {
        return try {
            val result = repository.getSimilarMovies(movieId)
            Result.Success(result)
        } catch (e: HttpException) {
            Result.Error(e.message(), e)
        } catch (e: IOException) {
            Result.Error(e.localizedMessage ?: "Unknown Error", e)
        } catch (e: Exception) {
            Result.Error(e.localizedMessage ?: "Unknown Error", e)
        }
    }
}