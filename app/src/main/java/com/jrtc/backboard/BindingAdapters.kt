package com.jrtc.backboard

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jrtc.backboard.network.Game
import com.jrtc.backboard.network.Child
import com.jrtc.backboard.ui.games.GameListAdapter
import com.jrtc.backboard.ui.highlights.HighlightListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Game>?) {
    val adapter = recyclerView.adapter as GameListAdapter
    adapter.submitList(data)
}

@BindingAdapter("highlightlistData")
fun bindHighlightRecyclerView(recyclerView: RecyclerView, data: List<Child>?) {
    val adapter = recyclerView.adapter as HighlightListAdapter
    adapter.submitList(data)
}