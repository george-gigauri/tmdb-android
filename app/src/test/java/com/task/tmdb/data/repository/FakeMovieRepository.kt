package com.task.tmdb.data.repository

import com.task.tmdb.domain.model.CreatedBy
import com.task.tmdb.domain.model.Genre
import com.task.tmdb.domain.model.Movie
import com.task.tmdb.domain.model.MovieDetails
import com.task.tmdb.domain.model.Network
import com.task.tmdb.domain.model.ProductionCompany
import com.task.tmdb.domain.model.ProductionCountry
import com.task.tmdb.domain.model.Season
import com.task.tmdb.domain.repository.MovieRepository
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.util.Date
import java.util.UUID
import kotlin.random.Random

open class FakeMovieRepository : MovieRepository {

    private fun makeRandomMovieList(): List<Movie> {
        return (5..(Random.nextInt(30))).map {
            Movie(
                Random.nextInt(),
                UUID.randomUUID().toString(),
                Date(System.currentTimeMillis()),
                listOf(1, 2, 3),
                listOf("Germany", "USA"),
                "ENG",
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                Random.nextDouble(99999.99),
                null,
                Random.nextDouble(10.0),
                Random.nextInt()
            )
        }
    }

    override suspend fun getPopularTVShows(page: Int): List<Movie> {
        return makeRandomMovieList()
    }

    override suspend fun getMovieDetails(movieId: Long): MovieDetails {
        return MovieDetails(
            Random.nextInt(),
            UUID.randomUUID().toString(),
            Random.nextBoolean(),
            listOf(
                CreatedBy(
                    Random.nextInt(),
                    UUID.randomUUID().toString(),
                    null
                )
            ),
            (1..(Random.nextInt(10))).toList(),
            Date(System.currentTimeMillis()),
            listOf(Genre("Horror")),
            "",
            Random.nextBoolean(),
            listOf("ENG", "KOR", "GEO", "RUS"),
            Date(System.currentTimeMillis()),
            null,
            listOf(Network(Random.nextInt(), UUID.randomUUID().toString(), null, "USA")),
            null,
            0,
            0,
            listOf("BR", "GR", "CN"),
            "ENG",
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            Random.nextDouble(),
            null,
            listOf(ProductionCompany(Random.nextInt(), null, UUID.randomUUID().toString())),
            listOf(ProductionCountry(UUID.randomUUID().toString())),
            listOf(
                Season(
                    Random.nextInt(),
                    UUID.randomUUID().toString(),
                    Random.nextInt(10),
                    "",
                    "",
                    Random.nextInt(15),
                    Date(System.currentTimeMillis())
                )
            ),
            emptyList(),
            "",
            "",
            Random.nextDouble(10.0),
            Random.nextInt()
        )
    }

    override suspend fun getSimilarMovies(movieId: Long): List<Movie> {
        return makeRandomMovieList()
    }
}