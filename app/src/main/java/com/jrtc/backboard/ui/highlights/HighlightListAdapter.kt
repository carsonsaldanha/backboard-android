package com.jrtc.backboard.ui.highlights

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jrtc.backboard.databinding.HighlightsListItemBinding
import com.jrtc.backboard.network.Post
import com.squareup.picasso.Picasso

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class HighlightListAdapter(private val clickListener: HighlightListener) :
    ListAdapter<Post, HighlightListAdapter.HighlightViewHolder>(DiffCallback) {

    class HighlightViewHolder(var binding: HighlightsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: HighlightListener, highlight: Post) {
            binding.highlight = highlight
            binding.clickListener = clickListener
            binding.executePendingBindings()

            // Loads the image thumbnails
            Picasso.get().load(highlight.data.media?.embed?.thumbnailUrl)
                .into(binding.highlightThumbnailImageView)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HighlightViewHolder(HighlightsListItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: HighlightViewHolder, position: Int) {
        val highlight = getItem(position)
        holder.bind(clickListener, highlight)
        // Sets the card to open the Reddit post on long press
        holder.binding.highlightCardView.setOnLongClickListener {
            val postUrl = "https://www.reddit.com${highlight.data.permalink}"
            val queryUrl: Uri = Uri.parse(postUrl)
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            holder.itemView.context.startActivity(intent)
            true
        }
    }

}

class HighlightListener(val clickListener: (highlight: Post) -> Unit) {
    fun onClick(highlight: Post) = clickListener(highlight)
}
