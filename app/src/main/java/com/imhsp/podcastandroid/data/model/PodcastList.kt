package com.imhsp.podcastandroid.data.model

data class PodcastList(val data: List<ResponseObject>)
data class ResponseObject(val results: List<Result>)
data class Result(
    val id: String,
    val thumbnail: String,
    val image: String,
    val podcast: List<Podcast>,
    val title_original: String,
    val description_original: String
)

data class Podcast(val publisher_original: String)
