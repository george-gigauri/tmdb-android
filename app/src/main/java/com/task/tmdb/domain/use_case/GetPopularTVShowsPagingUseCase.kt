package com.task.tmdb.domain.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.task.tmdb.common.Result
import com.task.tmdb.data.remote.paging.PopularTVShowsPagingSource
import com.task.tmdb.domain.model.Movie
import com.task.tmdb.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPopularTVShowsPagingUseCase @Inject constructor(
    private val source: PopularTVShowsPagingSource
) {

    operator fun invoke(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = { source }
        ).flow
    }
}