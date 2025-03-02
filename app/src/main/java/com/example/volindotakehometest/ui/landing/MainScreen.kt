package com.example.volindotakehometest.ui.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.ui.PlayerView
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.volindotakehometest.ui.landing.components.PostView
import com.example.volindotakehometest.ui.landing.controllers.VideoPlayerController
import com.example.volindotakehometest.ui.plus
import kotlinx.coroutines.flow.flow

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    MainScreen(
        uiState = uiState,
        modifier = modifier
    )
}

@Composable
fun MainScreen(
    uiState: MainUIState,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val videoPlayerController = remember { VideoPlayerController(context) }
    val feed = remember(uiState.feed) {
        flow { emit(uiState.feed) }
    }.collectAsLazyPagingItems()

    DisposableEffect(videoPlayerController) {
        onDispose {
            videoPlayerController.release()
        }
    }

    LazyColumn(
        state = rememberLazyListState(),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(32.dp),
        contentPadding = WindowInsets.navigationBars.asPaddingValues() + PaddingValues(vertical = 12.dp)
    ) {
        items(feed.itemCount) { index ->
            val post = feed[index]
            if (post != null) {
                PostView(
                    post = post,
                    videoPlayerController = videoPlayerController,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}