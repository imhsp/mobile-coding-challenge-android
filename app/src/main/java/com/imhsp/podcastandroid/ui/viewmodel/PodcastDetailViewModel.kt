package com.imhsp.podcastandroid.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imhsp.podcastandroid.data.FavouriteDAO
import com.imhsp.podcastandroid.data.PodcastRepository
import com.imhsp.podcastandroid.data.Result
import com.imhsp.podcastandroid.data.model.Favourite
import com.imhsp.podcastandroid.data.model.PodcastDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PodcastDetailViewModel @Inject constructor(
    private val repository: PodcastRepository,
    private val favouriteDAO: FavouriteDAO
) : ViewModel() {

    private val _podDetails = MutableLiveData<UIDataState>()
    val podDetails: LiveData<UIDataState>
        get() = _podDetails

    private val _favourite = MutableLiveData<Boolean>()
    val favourite: LiveData<Boolean>
        get() = _favourite

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

            val favourite = getFavouriteList()

            when (response) {
                is Result.Success<PodcastDetail> -> {
                    val data = response.data
                    _favourite.value = favourite.any { it.id == id }
                    _podDetails.value = UIDataState.Success(data)
                }
                is Result.Error -> {
                    _podDetails.value = UIDataState.Failure(response.exception)
                }
            }
        }
    }

    private suspend fun getFavouriteList(): List<Favourite>{
        return withContext(Dispatchers.IO) { favouriteDAO.getAllFavourites() }
    }

    fun setFavourite(id: String?) {
        if (id.isNullOrEmpty()) {
            _favourite.value = false
            return
        }
        viewModelScope.launch {
            try {
                favouriteDAO.insert(Favourite(id))
                _favourite.value = true
            } catch (e: Exception) {
                _favourite.value = false
            }
        }
    }

    fun removeFavourite(id: String?) {
        if (id.isNullOrEmpty()) {
            _favourite.value = true
            return
        }
        viewModelScope.launch {
            try {
                favouriteDAO.delete(Favourite(id))
                _favourite.value = false
            } catch (e: Exception) {
                _favourite.value = true
            }
        }
    }

}