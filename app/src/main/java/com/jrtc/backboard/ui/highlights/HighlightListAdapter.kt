package com.jrtc.backboard.ui.highlights

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jrtc.backboard.databinding.HighlightsListItemBinding
import com.jrtc.backboard.network.Child
import com.jrtc.backboard.network.ChildData

class HighlightListAdapter(val clickListener: HighlightListener) :
    ListAdapter<Child, HighlightListAdapter.HighlightViewHolder>(DiffCallback) {

    class HighlightViewHolder(var binding: HighlightsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: HighlightListener, child: Child) {
            binding.child = child
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<Child>() {

        override fun areItemsTheSame(oldItem: Child, newItem: Child): Boolean {
            return oldItem.data.url == newItem.data.url
        }

        override fun areContentsTheSame(oldItem: Child, newItem: Child): Boolean {
            return oldItem.data.title == newItem.data.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HighlightViewHolder(
            HighlightsListItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HighlightViewHolder, position: Int) {
        val child = getItem(position)
        holder.bind(clickListener, child)
    }
}

class HighlightListener(val clickListener: (child: Child) -> Unit) {
    fun onClick(child: Child) = clickListener(child)
}
