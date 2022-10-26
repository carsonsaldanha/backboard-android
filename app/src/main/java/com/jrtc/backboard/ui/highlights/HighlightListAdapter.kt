package com.jrtc.backboard.ui.highlights

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jrtc.backboard.databinding.GamesListItemBinding
import com.jrtc.backboard.network.ChildData

class HighlightListAdapter() :
    ListAdapter<ChildData, HighlightListAdapter.GameViewHolder>(DiffCallback) {

    class GameViewHolder(var binding: GamesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    companion object DiffCallback : DiffUtil.ItemCallback<ChildData>() {

        override fun areItemsTheSame(oldItem: ChildData, newItem: ChildData): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: ChildData, newItem: ChildData): Boolean {
            return oldItem.title == newItem.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GameViewHolder(
            GamesListItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = getItem(position)
    }
}
