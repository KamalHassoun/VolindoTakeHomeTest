package com.example.volindotakehometest.data.remote.api

import com.example.volindotakehometest.data.remote.response.ImgurEncapsulatedResponse
import com.example.volindotakehometest.data.remote.response.PostResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedApi {
    @GET("gallery/random/random")
    suspend fun getFeed(@Query("page") page: Int = 1): Response<ImgurEncapsulatedResponse<List<PostResponse>>>
}