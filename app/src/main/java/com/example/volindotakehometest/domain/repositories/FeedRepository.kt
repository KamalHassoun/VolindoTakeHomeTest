package com.example.volindotakehometest.domain.repositories

import androidx.paging.PagingData
import com.example.volindotakehometest.domain.models.Post
import kotlinx.coroutines.flow.Flow

interface FeedRepository {
    suspend fun getFeed(): Flow<PagingData<Post>>
}