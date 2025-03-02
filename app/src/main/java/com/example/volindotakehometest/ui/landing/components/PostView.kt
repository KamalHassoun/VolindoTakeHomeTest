package com.example.volindotakehometest.ui.landing.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.example.volindotakehometest.domain.models.Post
import com.example.volindotakehometest.domain.models.enums.MediaType
import com.example.volindotakehometest.ui.landing.controllers.VideoPlayerController
import com.google.accompanist.pager.HorizontalPagerIndicator

@Composable
fun PostView(
    post: Post,
    videoPlayerController: VideoPlayerController,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState { post.submissions.size }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = post.title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                beyondViewportPageCount = 2
            ) { page ->
                val submission = post.submissions[page]
                when (submission.type) {
                    MediaType.Image -> {
                        AsyncImage(
                            modifier = Modifier.fillMaxSize(),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(submission.url)
                                .decoderFactory(GifDecoder.Factory())
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Fit
                        )
                    }
                    MediaType.Video -> {
                        VideoPlayerView(
                            url = submission.url,
                            videoPlayerController = videoPlayerController
                        )
                    }
                }
            }
            if (post.submissions.size > 1) {
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    pageCount = post.submissions.size,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 12.dp),
                    activeColor = Color.Blue,
                    inactiveColor = Color.Gray,
                    indicatorWidth = 12.dp,
                    indicatorHeight = 8.dp
                )
            }
        }
    }
}