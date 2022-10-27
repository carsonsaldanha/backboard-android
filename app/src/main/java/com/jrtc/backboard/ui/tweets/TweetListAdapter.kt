package com.jrtc.backboard.ui.tweets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jrtc.backboard.databinding.TweetListItemBinding
import com.jrtc.backboard.network.Child

class TweetListAdapter (val clickListener: TweetListener) :
    ListAdapter<Child, TweetListAdapter.TweetViewHolder>(DiffCallback) {

    class TweetViewHolder (var binding: TweetListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: TweetListener, tweet: Child) {
            binding.tweet = tweet.data
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


class TweetListener (val clickListener: (tweet: Child) -> Unit){
    fun onClick(tweet: Child) = clickListener(tweet)
}
