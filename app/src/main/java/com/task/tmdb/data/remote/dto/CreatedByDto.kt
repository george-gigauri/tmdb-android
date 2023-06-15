package com.task.tmdb.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.task.tmdb.common.Const
import com.task.tmdb.domain.model.CreatedBy

data class CreatedByDto(
    @SerializedName("credit_id") val creditId: String,
    @SerializedName("gender") val gender: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("profile_path") val profilePath: String
)