package com.jrtc.backboard

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jrtc.backboard.network.Game
import com.jrtc.backboard.ui.games.GameListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Game>?) {
    val adapter = recyclerView.adapter as GameListAdapter
    adapter.submitList(data)
}