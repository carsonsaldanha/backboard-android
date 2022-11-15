package com.jrtc.backboard.network

import com.squareup.moshi.Json

/**
 * This data class defines [RedditResponse] which includes the data.
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */
data class RedditResponse(
    val data: RedditData
)

/**
 * This data class defines [RedditData] which includes the list of posts.
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */
data class RedditData(
    @Json(name = "children")
    val posts: List<Post>
)

/**
 * This data class defines [Post] which includes the data.
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */
data class Post(
    val data: PostData
)

/**
 * This data class defines [PostData] which includes the title of a Reddit post, media, and url.
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */
data class PostData(
    val title: String,
    @Json(name = "secure_media")
    val media: Media?,
    val permalink: String,
    val url: String
)

/**
 * This data class defines [Media] which includes the embed information of a Reddit post.
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */
data class Media(
    @Json(name = "oembed")
    val embed: Embed?
)

/**
 * This data class defines [Embed] which includes the thumbnail url of a Reddit post.
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */
data class Embed(
    @Json(name = "thumbnail_url")
    val thumbnailUrl: String?
)
