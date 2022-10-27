package com.jrtc.backboard.ui.tweets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jrtc.backboard.databinding.TweetListItemBinding
import com.jrtc.backboard.network.ChildData

class TweetListAdapter (val clickListener: TweetListener) :
    ListAdapter<ChildData, TweetListAdapter.TweetViewHolder>(DiffCallback) {

    class TweetViewHolder (var binding: TweetListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: TweetListener, tweet: ChildData) {
            binding.tweet = tweet
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ChildData>() {
        override fun areItemsTheSame(oldItem: ChildData, newItem: ChildData): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: ChildData, newItem: ChildData): Boolean {
            return oldItem.title == newItem.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TweetViewHolder(
            TweetListItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        val tweet = getItem(position)
        holder.bind(clickListener, tweet)
    }
}


class TweetListener (val clickListener: (tweet: ChildData) -> Unit){
    fun onClick(tweet: ChildData) = clickListener(tweet)
}
