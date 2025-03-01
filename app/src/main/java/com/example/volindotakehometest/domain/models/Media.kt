package com.example.volindotakehometest.domain.models

import com.example.volindotakehometest.domain.models.enums.MediaType

data class Media(
    val type: MediaType,
    val url: String
)
