package com.valentinerutto.myvideoplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.valentinerutto.myvideoplayer.ui.VideoListScreen
import com.valentinerutto.myvideoplayer.ui.VideoViewModel
import com.valentinerutto.myvideoplayer.ui.theme.MyVideoPlayerTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    val viewmodel: VideoViewModel by viewModel<VideoViewModel>()
    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            viewmodel.getVideosList()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyVideoPlayerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val videosList = viewmodel.videos.value

                    VideoListScreen(list = videosList)

                    // val url ="https://vz-56631916-b83.b-cdn.net/e4927a9c-5717-46ac-bbfa-136b4de9a572/playlist.m3u8"
                    val url =
                        "https://vz-56631916-b83.b-cdn.net/e4927a9c-5717-46ac-bbfa-136b4de9a572/play_720p.mp4"
                    //  VideoPlayerExoCompossable(videoUrl = url)

                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyVideoPlayerTheme {
        Greeting("Android")
    }
}