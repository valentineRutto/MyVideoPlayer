package com.valentinerutto.myvideoplayer.data

import com.valentinerutto.myvideoplayer.data.network.ApiService
import com.valentinerutto.myvideoplayer.utils.Resource
import com.valentinerutto.myvideoplayer.utils.mapResponseToVideo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class VideoRepository(private val apiService: ApiService) {
    suspend fun fetchVideos(): Flow<Resource<List<Video>>> = flow {
        emit(Resource.Loading())

        try {

            val remoteResponse = apiService.getVideoList()

            if (remoteResponse.isSuccessful.not()) {
                emit(
                    Resource.Error("something went wrong: ${remoteResponse.message()}")
                )
            }

            val videoList = mapResponseToVideo(remoteResponse.body()!!)


            emit(Resource.Success(videoList))


        } catch (e: HttpException) {
            emit(
                Resource.Error("something went wrong: ${e.message()}")
            )
        } catch (e: IOException) {
            emit(
                Resource.Error("something went wrong:${e.message}")
            )
        }
    }
}