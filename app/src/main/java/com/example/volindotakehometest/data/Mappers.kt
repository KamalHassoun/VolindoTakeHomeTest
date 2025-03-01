package com.example.volindotakehometest.data

import com.example.volindotakehometest.data.remote.response.MediaResponse
import com.example.volindotakehometest.data.remote.response.PostResponse
import com.example.volindotakehometest.domain.models.Media
import com.example.volindotakehometest.domain.models.Post
import com.example.volindotakehometest.domain.models.enums.MediaType

fun MediaResponse.toMedia(): Media {
    val type = if (this.type.startsWith("image/")) MediaType.Image else MediaType.Video
    return Media(
        type = type,
        url = if (type == MediaType.Image) link else mp4!!
    )
}

fun PostResponse.toPost(): Post {
    val submissions = arrayListOf<Media>()
    if (this.type != null) {
        val type = if (this.type.startsWith("image/")) MediaType.Image else MediaType.Video
        submissions.add(
            Media(
                type = type,
                url = link
            )
        )
    } else {
        submissions.addAll(this.images.map { m -> m.toMedia() })
    }
    return Post(
        title = this.title,
        submissions = submissions
    )
}