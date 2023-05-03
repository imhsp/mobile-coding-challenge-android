package com.imhsp.podcastandroid.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.imhsp.podcastandroid.data.model.PodcastDetail
import com.imhsp.podcastandroid.data.model.Results
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PodcastRepository @Inject constructor(private val service: PodcastService) {

    fun getPodcastItems(): Flow<PagingData<Results>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { PodcastPagingSource(service) }
        ).flow
    }

    suspend fun getPodcastDetails(id: String): Result<PodcastDetail> {
        return withContext(Dispatchers.IO) {
            try {
                val result = service.getPodcastDetails(id)
                Result.Success(result)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }
}