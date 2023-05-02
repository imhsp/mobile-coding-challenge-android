package com.imhsp.podcastandroid.data

import com.imhsp.podcastandroid.data.model.PodcastResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PodcastService {

    @GET("search")
    suspend fun getPodcastList(
        @Query("type") type: String = "podcast",
        @Query("offset") pageSize: Int,
    ): PodcastResponse

    @GET("podcasts/{id}")
    suspend fun getPodcastDetails(
        @Path("id") id: String
    ): PodcastResponse

    companion object {
        private const val BASE_URL = "https://listen-api-test.listennotes.com/api/v2/"

        fun create(): PodcastService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PodcastService::class.java)
        }
    }
}