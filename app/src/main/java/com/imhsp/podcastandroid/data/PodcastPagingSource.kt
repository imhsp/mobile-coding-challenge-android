package com.imhsp.podcastandroid.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.imhsp.podcastandroid.data.model.Results

private const val STARTING_PAGE_INDEX = 1

class PodcastPagingSource(private val service: PodcastService) : PagingSource<Int, Results>() {

    private var nextOffset = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getPodcastList(pageSize = nextOffset)
            nextOffset = response.nextOffset
            val pod = response.results
            LoadResult.Page(
                data = pod,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                // Due to mock API added below condition to stop endless loading, it will load only 3 pages
                nextKey = if (page == 3) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition
    }
}
