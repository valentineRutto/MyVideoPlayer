package com.valentinerutto.myvideoplayer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.valentinerutto.myvideoplayer.ui.MainView
import com.valentinerutto.myvideoplayer.ui.VideoPlayerExoCompossable
import com.valentinerutto.myvideoplayer.ui.VideoViewModel

@Composable
fun NavGraph(
    navController: NavHostController, modifier: Modifier, uiState: VideoViewModel.UiState,
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.VideoList.route,
        modifier = modifier
    ) {

        composable(route = NavigationItem.VideoList.route) {

            MainView(uiState = uiState, onItemSelected = {
                val route = NavigationItem.VideoDetails.createRoute(it)
                navController.navigate(route)
            })
        }

        composable(route = NavigationItem.VideoDetails.route) { backstackEntry ->

            val videoItemPosition =
                backstackEntry.arguments?.getString("videoItemPosition")?.toInt() ?: 0

            val videoItem = uiState.videos[videoItemPosition]
            VideoPlayerExoCompossable(
                modifier, videoItem
            )

        }
    }
}