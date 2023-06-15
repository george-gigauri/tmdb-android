package com.task.tmdb.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.task.tmdb.BuildConfig
import com.task.tmdb.common.Const
import com.task.tmdb.data.remote.service.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppContext(@ApplicationContext context: Context): Context = context

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Const.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor.Builder(context).build())
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
            val token = BuildConfig.TMDB_API_KEY
            request.addHeader(Const.HEADER_AUTHORIZATION, "Bearer $token")
            chain.proceed(request.build())
        }
        .build()

    @Provides
    @Singleton
    fun provideMoviesApi(retrofit: Retrofit): MovieApi = retrofit.create()
}