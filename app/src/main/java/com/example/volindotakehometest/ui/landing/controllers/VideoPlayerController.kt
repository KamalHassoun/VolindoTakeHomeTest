package com.example.volindotakehometest.ui.landing.controllers

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.database.DatabaseProvider
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import java.io.File

@SuppressLint("UnsafeOptInUsageError")
class VideoPlayerController(context: Context) {
    val exoPlayer: ExoPlayer = ExoPlayer.Builder(context).build()
    private val cacheFactory: CacheDataSource.Factory = setupExoPlayerCache(context)

    init {
        exoPlayer.playWhenReady = false
        exoPlayer.setMediaItem(MediaItem.fromUri(Uri.EMPTY))
    }

    fun playVideo(url: String) {
        val mediaItem = MediaItem.fromUri(url)

        //Check Cache first before downloading video from URL
        val mediaSourceFactory = ProgressiveMediaSource.Factory(cacheFactory)
        val mediaSource = mediaSourceFactory.createMediaSource(mediaItem)

        exoPlayer.setMediaSource(mediaSource)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }

    fun stopVideo() {
        exoPlayer.playWhenReady = false
        exoPlayer.stop()
    }

    fun release() {
        exoPlayer.release()
    }

    //Setup the caching instance
    private fun setupExoPlayerCache(context: Context): CacheDataSource.Factory {
        val databaseProvider: DatabaseProvider = StandaloneDatabaseProvider(context)
        val simpleCache = SimpleCache(
            File(context.cacheDir, "video_cache"), //Video Caching Folder
            LeastRecentlyUsedCacheEvictor((100 * 1024 * 1024).toLong()),
            databaseProvider
        )

        val upstreamFactory = DefaultDataSource.Factory(context)
        return CacheDataSource.Factory().apply {
            setCache(simpleCache)
            setUpstreamDataSourceFactory(upstreamFactory)
            setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)
        }
    }
}