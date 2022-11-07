package com.jrtc.backboard.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://www.reddit.com/"

// Builds the Moshi object with Kotlin adapter factory that Retrofit will be using to parse JSON
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Builds a Retrofit object with the Moshi converter
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getNBATweets] and [getNBAHighlights] methods.
 */
interface RedditApiService {
    /**
     * Returns a [Call] of [RedditResponse] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the specified endpoint will be requested with the GET
     * HTTP method.
     */
    @GET("search/.json?q=subreddit%3Anba%20site%3Atwitter.com&sort=hot")
    fun getNBATweets(): Call<RedditResponse>

    /**
     * Returns a [Call] of [RedditResponse] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the specified endpoint will be requested with the GET
     * HTTP method.
     */
    @GET("search/.json?q=subreddit%3Anba%20site%3Astreamable.com&sort=hot")
    fun getNBAHighlights(): Call<RedditResponse>
}

/**
 * A public Reddit Api object that exposes the lazy-initialized Retrofit service
 */
object RedditApi {
    val retrofitService: RedditApiService by lazy { retrofit.create(RedditApiService::class.java) }
}
