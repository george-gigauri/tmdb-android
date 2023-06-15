package com.task.tmdb.data.mapper

import com.task.tmdb.data.remote.dto.SpokenLanguageDto
import com.task.tmdb.domain.model.SpokenLanguage

fun SpokenLanguageDto.toDomain(): SpokenLanguage {
    return SpokenLanguage(name, englishName)
}