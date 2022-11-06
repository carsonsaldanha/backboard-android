package com.jrtc.backboard.ui.highlights

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jrtc.backboard.databinding.HighlightsListItemBinding
import com.jrtc.backboard.network.Post

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class HighlightListAdapter() :
    ListAdapter<Post, HighlightListAdapter.HighlightViewHolder>(DiffCallback) {

    class HighlightViewHolder(var binding: HighlightsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetJavaScriptEnabled")
        fun bind(highlight: Post) {
            binding.highlight = highlight.data
            binding.executePendingBindings()

            val highlightVideoURL = highlight.data.url
            val streamableVideoId = highlightVideoURL.subSequence(23, highlightVideoURL.length)
            val videoHTML = "<html>\n" +
                    "  <head>\n" +
                    "    <style type=\"text/css\">\n" +
                    "      html,\n" +
                    "      body {\n" +
                    "        height: 100%;\n" +
                    "        width: 100%;\n" +
                    "        margin: 0;\n" +
                    "        padding: 0;\n" +
                    "      }\n" +
                    "\n" +
                    "      .frame {\n" +
                    "        position: relative;\n" +
                    "        width: 100%;\n" +
                    "        height: 0;\n" +
                    "        padding-bottom: 56.25%;\n" +
                    "      }\n" +
                    "\n" +
                    "      iframe {\n" +
                    "        position: absolute;\n" +
                    "        top: 0;\n" +
                    "        left: 0;\n" +
                    "        overflow: hidden;\n" +
                    "      }\n" +
                    "    </style>\n" +
                    "  </head>\n" +
                    "  <body>\n" +
                    "    <div class='frame'>\n" +
                    "      <iframe src=\"https://streamable.com/e/$streamableVideoId\" width='100%' height='100%' allowfullscreen frameborder='0' scrolling='no'></iframe>\n" +
                    "    </div>\n" +
                    "  </body>\n" +
                    "</html>"
            binding.highlightWebView.settings.javaScriptEnabled = true
            binding.highlightWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null)
            binding.highlightWebView.loadData(videoHTML, "text/html", null)
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
        holder.bind(highlight)
    }

}
