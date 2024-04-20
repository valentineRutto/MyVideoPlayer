package com.valentinerutto.myvideoplayer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valentinerutto.myvideoplayer.data.Video
import com.valentinerutto.myvideoplayer.data.VideoRepository
import com.valentinerutto.myvideoplayer.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VideoViewModel(private val repository: VideoRepository) : ViewModel() {

    private val _state = MutableStateFlow(UiState(loading = true))
    val state: StateFlow<UiState> = _state.asStateFlow()


    suspend fun getVideosList() = viewModelScope.launch(Dispatchers.IO) {

        repository.fetchVideos().collect {
            when (it) {
                is Resource.Loading -> {

                    setState {
                        copy(loading = true)
                    }
                }

                is Resource.Success -> {
                    setState {
                        copy(loading = false, videos = it.data)
                    }
                }

                is Resource.Error -> {
                    setState {
                        copy(loading = false, error = it.errorMessage)
                    }
                }

            }
        }
    }

    private fun setState(stateReducer: UiState.() -> UiState) {
        viewModelScope.launch {
            _state.emit(stateReducer(state.value))
        }
    }

    data class UiState(
        val loading: Boolean = false, val videos: List<Video> = emptyList(), val error: String = ""
    )
}