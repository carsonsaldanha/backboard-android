package com.jrtc.backboard.network

import com.squareup.moshi.Json

/**
 * This data class defines [StreamableResponse] which includes the video files.
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */
data class StreamableResponse(
    val files: Files
)

data class Files(
    val mp4: MP4,
    @Json(name = "mp4-mobile")
    val mp4Mobile: MP4?
)

data class MP4(
    val url: String
)
