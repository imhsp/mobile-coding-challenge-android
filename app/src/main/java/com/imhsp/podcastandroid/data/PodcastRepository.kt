package com.imhsp.podcastandroid.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.imhsp.podcastandroid.data.model.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PodcastRepository @Inject constructor(private val service: PodcastService) {

    fun getPodcastItems(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { PodcastPagingSource(service) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }
}