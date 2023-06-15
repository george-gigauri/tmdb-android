package com.task.tmdb.domain.use_case

import com.task.tmdb.common.Result
import com.task.tmdb.data.repository.FakeMovieRepository
import com.task.tmdb.domain.model.CreatedBy
import com.task.tmdb.domain.model.Genre
import com.task.tmdb.domain.model.MovieDetails
import com.task.tmdb.domain.model.Network
import com.task.tmdb.domain.model.ProductionCompany
import com.task.tmdb.domain.model.ProductionCountry
import com.task.tmdb.domain.model.Season
import com.task.tmdb.domain.repository.MovieRepository
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.util.Date
import java.util.UUID
import kotlin.random.Random

class GetMovieDetailsUseCaseTest {

    private lateinit var repo: MovieRepository
    private lateinit var useCase: GetMovieDetailsUseCase

    @Before
    fun setUp() {
        repo = mock(FakeMovieRepository::class.java)
        useCase = GetMovieDetailsUseCase(repo)
    }

    @Test
    fun `get movie details return success`() {
        val result = runBlocking { useCase(-1) }
        assert(result is Result.Success)
    }

    @Test
    fun `get movie details return io error`() {
        runBlocking {
            `when`(useCase(-1)).thenReturn(
                Result.Error(
                    "No Connection",
                    IOException("No Connection")
                )
            )
            val result = useCase(-1)
            assert(result is Result.Error && result.t is IOException)
        }
    }

    @Test
    fun `get movie details return http server error`() {
        runBlocking {
            `when`(useCase(-1)).thenThrow(
                HttpException(
                    Response.error<Any>(
                        500,
                        ResponseBody.Companion.create(null, "Internal Server Error")
                    )
                )
            )

            val result = useCase(-1)
            assert(result is Result.Error && result.t is HttpException)
        }
    }
}