package com.example.volindotakehometest.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.volindotakehometest.data.remote.paging.ImgurPagingSource
import com.example.volindotakehometest.data.remote.api.FeedApi
import com.example.volindotakehometest.data.toPost
import com.example.volindotakehometest.domain.models.Post
import com.example.volindotakehometest.domain.repositories.FeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(
    private val feedApi: FeedApi
): FeedRepository {
    override suspend fun getFeed(): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(
                pageSize = 60,
                prefetchDistance = 20
            ),
            pagingSourceFactory = {
                ImgurPagingSource(
                    action = { page ->
                        feedApi.getFeed(page = page)
                    }
                )
            }
        ).flow.map { pagingData -> pagingData.map { post -> post.toPost() } }
    }
}