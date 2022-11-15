package com.jrtc.backboard.ui.tweets

import android.content.Intent
import android.net.Uri
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
        // Sets the card to open the tweet link on single press
        holder.binding.tweetCardView.setOnClickListener {
            val queryUrl: Uri = Uri.parse(tweet.data.url)
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            holder.itemView.context.startActivity(intent)
        }
        // Sets the card to open the Reddit post on long press
        holder.binding.tweetCardView.setOnLongClickListener {
            val postUrl = "https://www.reddit.com${tweet.data.permalink}"
            val queryUrl: Uri = Uri.parse(postUrl)
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            holder.itemView.context.startActivity(intent)
            true
        }
    }

}

class TweetListener(val clickListener: (tweet: Post) -> Unit) {
    fun onClick(tweet: Post) = clickListener(tweet)
}
