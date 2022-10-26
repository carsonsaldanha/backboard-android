package com.jrtc.backboard.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://www.reddit.com/search/.json"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface RedditApiService {
    @GET("?q=subreddit%3Anba%20site%3Astreamable.com&sort=hot")
    fun getNBAHighlights(): Call<Data>

    @GET("?q=subreddit%3Anba%20site%3Atwitter.com&sort=hot")
    fun getNBATweets(): Call<Data>
}

object RedditApi {
    val retrofitService: RedditApiService by lazy { retrofit.create(RedditApiService::class.java) }
}