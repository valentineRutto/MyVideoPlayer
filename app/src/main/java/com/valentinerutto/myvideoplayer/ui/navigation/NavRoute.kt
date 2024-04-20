package com.valentinerutto.myvideoplayer.ui.navigation

sealed class NavigationItem(val route: String) {
    object VideoList : NavigationItem("videoList")
    object VideoDetails : NavigationItem("videoDetails/{videoItemPosition}") {
        fun createRoute(videoItemPosition: Int) = "videoDetails/$videoItemPosition"
    }
}