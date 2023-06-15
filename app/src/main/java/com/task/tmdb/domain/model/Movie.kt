package com.task.tmdb.domain.model

import java.util.Date

data class Movie(
    val id: Int,
    val name: String,
    val airDate: Date?,
    val genreIds: List<Int>,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterUrl: String?,
    val voteAverage: Double,
    val voteCount: Int
)
