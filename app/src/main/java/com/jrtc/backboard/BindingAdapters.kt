package com.jrtc.backboard

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jrtc.backboard.network.Child
import com.jrtc.backboard.network.Game
import com.jrtc.backboard.ui.games.GameListAdapter
import com.jrtc.backboard.ui.tweets.TweetListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Game>?) {
    val adapter = recyclerView.adapter as GameListAdapter
    adapter.submitList(data)
}

@BindingAdapter("tweetListData")
fun bindTweetRecyclerView(recyclerView: RecyclerView, data: List<Child>?) {
    val adapter = recyclerView.adapter as TweetListAdapter
    adapter.submitList(data)
}