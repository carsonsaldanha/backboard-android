package com.jrtc.backboard.network

/**
 * This data class defines [StreamableResponse] which includes the video files.
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */
data class StreamableResponse(
    val files: Files
)

/**
 * This data class defines [Files] which includes the MP4 data.
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */
data class Files(
    val mp4: MP4,
)

/**
 * This data class defines [MP4] which includes the video url.
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */
data class MP4(
    val url: String
)
