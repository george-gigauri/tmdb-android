package com.task.tmdb.data.remote.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.task.tmdb.data.mapper.toDomain
import com.task.tmdb.data.remote.service.MovieApi
import com.task.tmdb.domain.model.Movie
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class PopularTVShowsPagingSource @Inject constructor(
    private val api: MovieApi
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        val prevKey = if (page > 1) page - 1 else null

        return try {
            val result = api.getPopularTVSeries(page)
            val isLastPage = result.page == result.total_pages
            val nextKey = if (!isLastPage) page + 1 else null
            LoadResult.Page(result.results.map { it.toDomain() }, prevKey, nextKey)
        } catch (e: HttpException) {
            Log.d("PopularPagingSource", e.message())
            LoadResult.Error(e)
        } catch (e: IOException) {
            Log.d("PopularPagingSource", e.localizedMessage ?: e.message ?: "Unknown")
            LoadResult.Error(e)
        } catch (e: Exception) {
            Log.d("PopularPagingSource", e.localizedMessage ?: e.message ?: "Unknown")
            LoadResult.Error(e)
        }
    }
}