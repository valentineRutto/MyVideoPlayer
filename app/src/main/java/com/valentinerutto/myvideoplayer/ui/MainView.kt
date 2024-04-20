package com.valentinerutto.myvideoplayer.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@Composable
fun MainView(
    uiState: VideoViewModel.UiState,
    onItemSelected: (itemPosition: Int) -> Unit,
) {


    Column(modifier = Modifier.fillMaxSize()) {


        if (uiState.loading) {
            LoadingView()
        }

        if (uiState.error.isBlank().not()) {
            ErrorScreen(uiState, Modifier.fillMaxSize())
        }

        if (uiState.videos.isNotEmpty()) {
            VideoListScreen(
                onItemSelected = onItemSelected, Modifier.fillMaxSize(), uiState.videos
            )
        }
    }
}

@Composable
fun LoadingView() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = lightColorScheme().tertiary,
            trackColor = darkColorScheme().tertiary,
        )
    }
}

@Composable
fun ErrorScreen(uiState: VideoViewModel.UiState, modifier: Modifier) {

    val vm = koinViewModel<VideoViewModel>()
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

        Text(
            text = uiState.error,
            textAlign = TextAlign.Center,
            modifier = modifier,
            style = MaterialTheme.typography.bodyMedium
        )

        Button(
            onClick = {
                coroutineScope.launch {
                    vm.getVideosList()
                }
            }, modifier = Modifier.padding(4.dp)
        ) {
            Text(
                text = "Try Again",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}
