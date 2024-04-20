package com.valentinerutto.myvideoplayer.utils

import com.valentinerutto.myvideoplayer.data.Video

object MediaUtils {

    fun embedurl(video: Video) =
        "https://iframe.mediadelivery.net/embed/${video.videoLibraryId}/${video.guid}"

    fun hlsurl(video: Video) = "https://vz-56631916-b83.b-cdn.net/${video.guid}/playlist.m3u8"
    fun directPlayurl(video: Video) =
        "https://video.bunnycdn.com/play/${video.videoLibraryId}/${video.guid}"

    fun mp4url(video: Video) = "https://vz-56631916-b83.b-cdn.net/${video.guid}/play_480p.mp4"

}