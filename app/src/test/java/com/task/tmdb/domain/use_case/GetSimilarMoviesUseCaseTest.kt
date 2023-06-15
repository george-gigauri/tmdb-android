package com.task.tmdb.domain.use_case

import com.task.tmdb.common.Result
import com.task.tmdb.data.repository.FakeMovieRepository
import com.task.tmdb.domain.repository.MovieRepository
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.kotlin.given
import org.mockito.kotlin.whenever
import org.mockito.kotlin.willAnswer
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import kotlin.random.Random

class GetSimilarMoviesUseCaseTest {

    private lateinit var repo: MovieRepository
    private lateinit var useCase: GetSimilarMoviesUseCase

    @Before
    fun setUp() {
        repo = Mockito.mock(FakeMovieRepository::class.java)
        useCase = GetSimilarMoviesUseCase(repo)
    }

    @Test
    fun `get similar movies is success`() {
        runBlocking {
            val result = useCase(Random.nextLong())
            assert(result is Result.Success)
        }
    }

    @Test
    fun `get similar movies is io error`() {
        runBlocking {
            val id = Random.nextLong()
            given(useCase(id)).willAnswer { throw IOException("No internet") }
            val result = useCase(id)
            assert(result is Result.Error && result.t is IOException)
        }
    }

    @Test
    fun `get similar movies is http error`() {
        runBlocking {
            val id = Random.nextLong()
            given(useCase(id)).willAnswer {
                HttpException(
                    Response.error<Any>(
                        500,
                        ResponseBody.create(null, "Internal Server Error")
                    )
                )
            }
            val result = useCase(id)
            assert(result is Result.Error && result.t is HttpException)
        }
    }
}