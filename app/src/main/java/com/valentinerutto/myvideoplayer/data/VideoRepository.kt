package com.valentinerutto.myvideoplayer.data

import com.valentinerutto.myvideoplayer.data.network.ApiService
import com.valentinerutto.myvideoplayer.utils.Resource
import com.valentinerutto.myvideoplayer.utils.mapResponseToVideo

class VideoRepository(private val apiService: ApiService) {
    suspend fun fetchVideos(): Resource<List<Video>> {

        val response = apiService.getVideoList()

        if (!response.isSuccessful) {
            return Resource.Error(response.message())
        }

        val videoList = mapResponseToVideo(response.body()!!)

        return Resource.Success(videoList)
    }
}