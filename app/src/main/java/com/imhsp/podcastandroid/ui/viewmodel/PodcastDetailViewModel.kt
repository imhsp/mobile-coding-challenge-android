package com.imhsp.podcastandroid.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imhsp.podcastandroid.data.PodcastRepository
import com.imhsp.podcastandroid.data.Result
import com.imhsp.podcastandroid.data.model.PodcastDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PodcastDetailViewModel @Inject constructor(private val repository: PodcastRepository) : ViewModel() {

    private val _podDetails = MutableLiveData<UIDataState>()
    val podDetails: LiveData<UIDataState>
        get() = _podDetails

    fun getPodDetails(id: String?) {
        _podDetails.value = UIDataState.Loading
        if (id.isNullOrEmpty()) {
            _podDetails.value = UIDataState.Failure(Exception("Invalid ID"))
            return
        }
        viewModelScope.launch {
            val response = try {
                repository.getPodcastDetails(id)
            } catch (e: Exception) {
                Result.Error(Exception("Network request failed"))
            }

            when (response) {
                is Result.Success<PodcastDetail> -> {
                    _podDetails.value = UIDataState.Success(response.data)
                }
                is Result.Error -> {
                    _podDetails.value = UIDataState.Failure(response.exception)
                }
            }
        }
    }

}