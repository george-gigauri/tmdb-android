package com.task.tmdb.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.task.tmdb.domain.model.Genre

data class GenreDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)