package com.zee.streamingwithexoplayer.domain.model

data class VideoModel(
    val id:Long,
    val thumb: String,
    val sources: String,
    val title: String,
    val subtitle: String,
    val description: String
)
