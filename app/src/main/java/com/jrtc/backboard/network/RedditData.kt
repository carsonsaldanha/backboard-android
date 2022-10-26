package com.jrtc.backboard.network

data class RedditData (
    val data: Data
)

data class Data (
    val children: List<Child>
)

data class Child (
    val data: ChildData
)

data class ChildData (
    val title: String,
    val url: String
)

