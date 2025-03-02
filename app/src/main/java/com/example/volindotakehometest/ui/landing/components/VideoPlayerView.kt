package com.example.volindotakehometest.ui.landing.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.PlayerView
import com.example.volindotakehometest.ui.landing.controllers.VideoPlayerController

@Composable
fun VideoPlayerView(
    url: String,
    videoPlayerController: VideoPlayerController
) {
    val context = LocalContext.current
    val playerView = remember {
        PlayerView(context).apply {
            player = videoPlayerController.exoPlayer
            useController = false
        }
    }

    DisposableEffect(url) {
        videoPlayerController.playVideo(url)
        playerView.useController = true

        onDispose {
            videoPlayerController.stopVideo()
        }
    }

    AndroidView(
        factory = { playerView },
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    )
}