package com.example.volindotakehometest.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImgurEncapsulatedResponse<T>(
    @SerializedName("data")
    @Expose
    val data: T,

    @SerializedName("success")
    @Expose
    val success: Boolean,

    @SerializedName("status")
    @Expose
    val status: Int
)