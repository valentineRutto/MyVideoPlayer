package com.valentinerutto.myvideoplayer.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideosListResponse(
    @SerialName("currentPage")
    val currentPage: Int?,
    @SerialName("items")
    val items: List<Item?>?,
    @SerialName("itemsPerPage")
    val itemsPerPage: Int?,
    @SerialName("totalItems")
    val totalItems: Int?
) {
    @Serializable
    data class Item(
        @SerialName("availableResolutions")
        val availableResolutions: String?,
        @SerialName("averageWatchTime")
        val averageWatchTime: Int?,
        @SerialName("captions")
        val captions: List<Any?>?,
        @SerialName("category")
        val category: String?,
        @SerialName("chapters")
        val chapters: List<Any?>?,
        @SerialName("collectionId")
        val collectionId: String?,
        @SerialName("dateUploaded")
        val dateUploaded: String?,
        @SerialName("encodeProgress")
        val encodeProgress: Int?,
        @SerialName("framerate")
        val framerate: Int?,
        @SerialName("guid")
        val guid: String?,
        @SerialName("hasMP4Fallback")
        val hasMP4Fallback: Boolean?,
        @SerialName("height")
        val height: Int?,
        @SerialName("isPublic")
        val isPublic: Boolean?,
        @SerialName("length")
        val length: Int?,
        @SerialName("metaTags")
        val metaTags: List<Any?>?,
        @SerialName("moments")
        val moments: List<Any?>?,
        @SerialName("rotation")
        val rotation: Int?,
        @SerialName("status")
        val status: Int?,
        @SerialName("storageSize")
        val storageSize: Int?,
        @SerialName("thumbnailCount")
        val thumbnailCount: Int?,
        @SerialName("thumbnailFileName")
        val thumbnailFileName: String?,
        @SerialName("title")
        val title: String?,
        @SerialName("totalWatchTime")
        val totalWatchTime: Int?,
        @SerialName("transcodingMessages")
        val transcodingMessages: List<Any?>?,
        @SerialName("videoLibraryId")
        val videoLibraryId: Int?,
        @SerialName("views")
        val views: Int?,
        @SerialName("width")
        val width: Int?
    )
}