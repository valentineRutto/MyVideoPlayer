package com.valentinerutto.myvideoplayer.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.valentinerutto.myvideoplayer.data.Video

@Composable
fun VideoListScreen(
    modifier: Modifier = Modifier, list: List<Video>
) {
    Box(modifier) {
        Text(text = "VideoListScreen")

        LazyColumn {
            items(list) { video ->
                VideoListItem(video)
            }

        }
    }
}

@Composable
fun VideoListItem(video: Video) {
    val context = LocalContext.current
    // , navigateToVideo: (Video) -> Unit
    ElevatedCard(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {
        Row(Modifier.clickable {
            Toast.makeText(context, "go to video screen", Toast.LENGTH_LONG).show()
        }) {
            // navigateToVideo(video)
            ImageComposable(video.thumbnail)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = video.title, style = typography.bodyMedium)
                Text(text = video.length.toString(), style = typography.bodyMedium)
                Text(text = "Play video", style = typography.bodyLarge)
            }
        }
    }
}


@Composable
fun ImageComposable(imageUrl: String) {
    AsyncImage(
        alignment = Alignment.CenterStart,
        model = imageUrl,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp))),
        contentDescription = stringResource(androidx.media3.ui.R.string.exo_controls_play_description),
    )
}

@Preview(name = "VideoListScreen")
@Composable
private fun PreviewVideoListScreen() {
    //VideoListScreen()
}