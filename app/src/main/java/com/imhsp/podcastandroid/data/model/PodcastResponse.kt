package com.imhsp.podcastandroid.data.model

import com.google.gson.annotations.SerializedName

data class PodcastResponse(
    val results: List<Results>,
    @SerializedName("next_offset") val nextOffset: Int
)

data class Results(
    val id: String,
    val podcast: Podcast
)

data class Podcast(
    val id: String,
    val thumbnail: String,
    @SerializedName("title_original") val titleOriginal: String,
    @SerializedName("publisher_original") val publisherOriginal: String,
    var isFavourite: Boolean = false
)
