package com.example.volindotakehometest.ui.landing

import androidx.paging.PagingData
import com.example.volindotakehometest.domain.models.Post

data class MainUIState(
    val feed: PagingData<Post> = PagingData.empty()
)