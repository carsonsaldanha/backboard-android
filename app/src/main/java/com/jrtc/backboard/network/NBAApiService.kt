package com.jrtc.backboard.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://cdn.nba.com/static/json/liveData/"

// Builds the Moshi object with Kotlin adapter factory that Retrofit will be using to parse JSON
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Builds a Retrofit object with the Moshi converter
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface NBAApiService {
    @GET("scoreboard/todaysScoreboard_00.json")
    suspend fun getNBAGames(): TodaysGames
}

object NBAApi {
    val retrofitService: NBAApiService by lazy { retrofit.create(NBAApiService::class.java) }
}