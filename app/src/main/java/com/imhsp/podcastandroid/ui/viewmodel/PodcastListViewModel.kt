package com.imhsp.podcastandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.imhsp.podcastandroid.data.PodcastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PodcastListViewModel @Inject constructor(
    repository: PodcastRepository,
) : ViewModel() {
    val podcastList = repository.getPodcastItems().cachedIn(viewModelScope)
}