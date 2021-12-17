package com.example.cafeyntest.domains.network

data class ResponseItem(
    val id: Int,
    val albumId: Int?,
    val title: String?,
    val url: String?,
    val thumbnailUrl: String?
)
