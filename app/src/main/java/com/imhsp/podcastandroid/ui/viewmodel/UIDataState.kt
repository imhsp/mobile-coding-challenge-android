package com.imhsp.podcastandroid.ui.viewmodel

import com.imhsp.podcastandroid.data.model.PodcastDetail

open class UIDataState {
    object Loading : UIDataState()
    data class Success(val podcastDetail: PodcastDetail) : UIDataState()
    data class Failure(val exception: Exception) : UIDataState()
}