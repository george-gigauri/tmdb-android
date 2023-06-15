package com.task.tmdb.domain.use_case

import com.task.tmdb.common.Result
import com.task.tmdb.domain.model.MovieDetails
import com.task.tmdb.domain.repository.MovieRepository
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(id: Long): Result<MovieDetails> {
        return try {
            val result = repository.getMovieDetails(id)
            Result.Success(result)
        } catch (e: HttpException) {
            e.printStackTrace()
            Result.Error(e.message(), e)
        } catch (e: IOException) {
            e.printStackTrace()
            Result.Error(e.localizedMessage ?: "Unknown Error", e)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e.localizedMessage ?: "Unknown Error", e)
        }
    }
}