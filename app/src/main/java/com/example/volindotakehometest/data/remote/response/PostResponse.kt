package com.example.volindotakehometest.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("type")
    @Expose
    val type: String?,

    @SerializedName("link")
    @Expose
    val link: String,

    @SerializedName("images")
    @Expose
    val images: List<MediaResponse>
)
