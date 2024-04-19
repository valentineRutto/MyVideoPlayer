package com.valentinerutto.myvideoplayer.data.network

import com.valentinerutto.myvideoplayer.data.VideosListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/library/{libraryId}/videos")
    suspend fun getVideoList(
        @Path("libraryId") libraryId:Int = 232342,
        @Query("page") page: Int = 1,
        @Query("itemsPerPage") perPage: Int = 100
    ):Response<VideosListResponse>
}