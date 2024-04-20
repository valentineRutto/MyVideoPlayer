package com.valentinerutto.myvideoplayer.utils

import com.valentinerutto.myvideoplayer.data.Video
import com.valentinerutto.myvideoplayer.data.VideosListResponse

fun mapResponseToVideo(response: VideosListResponse): List<Video> {
    return response.items!!.map {

        Video(
            videoLibraryId = it?.videoLibraryId!!,
            guid = it.guid!!,
            title = it.title!!,
            thumbnail = it.thumbnailFileName!!,
            views = it.views!!,
            dateUploaded = it.dateUploaded!!,
            length = it.length!!
        )
    }
}





