package com.valentinerutto.myvideoplayer.ui

import android.content.Context
import android.content.Intent
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat.startActivity
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.PlayerView
import com.valentinerutto.myvideoplayer.data.Video
import com.valentinerutto.myvideoplayer.utils.MediaUtils

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayerExoCompossable(
    modifier: Modifier = Modifier, video: Video
) {

    val context = LocalContext.current
    val videoUrl = MediaUtils.mp4url(video)

    val dataSourceFactory = DataSource.Factory {

        val dataSource = DefaultHttpDataSource.Factory().createDataSource()
        dataSource
    }

    val mediaItem = MediaItem.fromUri(videoUrl)

    val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(mediaItem)


    val player = ExoPlayer.Builder(context).build().apply {
        setMediaSource(mediaSource)
    }

    val playerView = PlayerView(context)
    val playWhenReady by rememberSaveable {
        mutableStateOf(true)
    }

    playerView.player = player

    LaunchedEffect(player) {
        player.prepare()
        player.playWhenReady = playWhenReady
    }

    DisposableEffect(Unit) {
        onDispose {
            player.release()
        }
    }


    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            text = "Now Playing:" + video.title,
            textAlign = TextAlign.Center,
            modifier = modifier,
            style = MaterialTheme.typography.bodyMedium
        )

        Share(context = context,videoUrl)

        AndroidView(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)),
            factory = {
                playerView
            })


    }

}

@Composable
fun Share(context: Context,videourl:String) {

    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, videourl)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    Row {


        Button(onClick = {


        }) {
            Icon(imageVector = Icons.Filled.ThumbUp, contentDescription = null)
            Text("like", modifier = Modifier.padding(start = 8.dp))
        }
        Button(onClick = {

        }) {
            Icon(imageVector = Icons.Filled.Close, contentDescription = null)
            Text("dislike", modifier = Modifier.padding(start = 8.dp))
        }

        Button(onClick = {
            startActivity(context, shareIntent, null)
        }) {
            Icon(imageVector = Icons.Filled.Share, contentDescription = null)
            Text("Share", modifier = Modifier.padding(start = 8.dp))
        }
    }
}
