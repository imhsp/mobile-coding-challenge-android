package com.imhsp.podcastandroid.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.imhsp.podcastandroid.data.model.Result

private const val STARTING_PAGE_INDEX = 1

class PodcastPagingSource(private val service: PodcastService) : PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getPodcastList(pageSize = params.loadSize)

            val pod = response.results
            LoadResult.Page(
                data = pod,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == response.nextOffset / 10) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }
}
