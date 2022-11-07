package com.jrtc.backboard.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://api.streamable.com/"

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
 * A public interface that exposes the [getVideo] method.
 */
interface StreamableApiService {
    /**
     * Returns a [Call] of [StreamableResponse] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the specified endpoint will be requested with the GET
     * HTTP method.
     */
    @GET("videos/{videoId}")
    fun getVideo(@Path("videoId") videoId: String): Call<StreamableResponse>
}

/**
 * A public Streamable Api object that exposes the lazy-initialized Retrofit service
 */
object StreamableApi {
    val retrofitService: StreamableApiService by lazy { retrofit.create(StreamableApiService::class.java) }
}
