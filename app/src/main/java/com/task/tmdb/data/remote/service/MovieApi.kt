package com.task.tmdb.data.remote.service

import com.task.tmdb.data.remote.dto.PopularShowsResponse
import com.task.tmdb.data.remote.dto.TVShowDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("3/tv/popular")
    suspend fun getPopularTVSeries(
        @Query("page") page: Int,
        @Query("include_adult") includeAdultContent: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): PopularShowsResponse

    @GET("3/tv/{id}")
    suspend fun getDetails(
        @Path("id") id: Long
    ): TVShowDetailsDto

    @GET("3/tv/{id}/similar")
    suspend fun getSimilarMovies(@Path("id") movieId: Long): PopularShowsResponse
}