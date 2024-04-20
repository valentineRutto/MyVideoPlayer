package com.valentinerutto.myvideoplayer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinerutto.myvideoplayer.R
import com.valentinerutto.myvideoplayer.data.Video

@Composable
fun VideoListScreen(
    onItemSelected: (itemPosition: Int) -> Unit, modifier: Modifier = Modifier, videos: List<Video>
) {

    Box(Modifier.fillMaxSize()) {
        LazyColumn {
            itemsIndexed(videos) { index, video ->
                VideoListItem(modifier.clickable {
                    onItemSelected(index)
                }, video)
            }

        }
    }
}

@Composable
fun VideoListItem(
    modifier: Modifier = Modifier, video: Video
) {
    ElevatedCard(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {
        Row {
            ImageComposable()
            Column(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = video.title, style = typography.bodyMedium)
                Text(text = video.length.toString(), style = typography.bodyMedium)
                Text(text = video.dateUploaded, style = typography.bodyLarge)
            }
        }
    }
}

@Composable
fun ImageComposable() {
    Image(
        painter = painterResource(id = R.drawable.ic_play),
        alignment = Alignment.CenterStart,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp))),
        contentDescription = "play",
    )
}

@Preview(name = "VideoListScreen")
@Composable
private fun PreviewVideoListScreen() {
    //VideoListScreen()
}