package com.jrtc.backboard.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

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

/**
 * A public interface that exposes the [getGames] and [getBoxscore] methods.
 */
interface NBAApiService {
    /**
     * Returns a [Call] of [TodaysGamesResponse] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the specified endpoint will be requested with the GET
     * HTTP method.
     */
    @GET("scoreboard/todaysScoreboard_00.json")
    fun getGames(): Call<TodaysGamesResponse>

    @GET("boxscore/boxscore_{gameId}.json")
    fun getBoxscore(@Path("gameId") gameId: String): Call<Boxscore>
}

/**
 * A public NBA Api object that exposes the lazy-initialized Retrofit service
 */
object NBAApi {
    val retrofitService: NBAApiService by lazy { retrofit.create(NBAApiService::class.java) }
}
