package com.example.volindotakehometest.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MediaResponse(
    @SerializedName("type")
    @Expose
    val type: String,

    @SerializedName("link")
    @Expose
    val link: String,

    @SerializedName("mp4")
    @Expose
    val mp4: String?
)
