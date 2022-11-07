package com.jrtc.backboard.ui.tweets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jrtc.backboard.databinding.TweetsListItemBinding
import com.jrtc.backboard.network.Post

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class TweetListAdapter(private val clickListener: TweetListener) :
    ListAdapter<Post, TweetListAdapter.TweetViewHolder>(DiffCallback) {

    class TweetViewHolder(var binding: TweetsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: TweetListener, tweet: Post) {
            binding.tweet = tweet
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.data.url == newItem.data.url
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.data.title == newItem.data.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TweetViewHolder(TweetsListItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        val tweet = getItem(position)
        holder.bind(clickListener, tweet)
    }

}

class TweetListener(val clickListener: (tweet: Post) -> Unit) {
    fun onClick(tweet: Post) = clickListener(tweet)
}
