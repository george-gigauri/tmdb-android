package com.task.tmdb.di

import com.task.tmdb.data.repository.MovieRepositoryImpl
import com.task.tmdb.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(repo: MovieRepositoryImpl): MovieRepository = repo
}