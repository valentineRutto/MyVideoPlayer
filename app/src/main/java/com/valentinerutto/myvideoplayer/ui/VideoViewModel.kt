package com.valentinerutto.myvideoplayer.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.valentinerutto.myvideoplayer.data.Video
import com.valentinerutto.myvideoplayer.data.VideoRepository
import com.valentinerutto.myvideoplayer.utils.Resource

class VideoViewModel(private val repository: VideoRepository):ViewModel() {
   val videos: MutableState<List<Video>> = mutableStateOf(emptyList())

   suspend fun getVideosList(){
   when( val response= repository.fetchVideos()){
      is Resource.Error -> {

      }
      is Resource.Loading -> {

      }

      is Resource.Success -> {
         videos.value = response.data
      }
   }
}
}