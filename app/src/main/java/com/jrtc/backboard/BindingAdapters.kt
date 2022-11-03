package com.jrtc.backboard

import android.widget.TableLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jrtc.backboard.network.Game
import com.jrtc.backboard.network.Player
import com.jrtc.backboard.network.Post
import com.jrtc.backboard.ui.games.GameListAdapter
import com.jrtc.backboard.ui.highlights.HighlightListAdapter
import com.jrtc.backboard.ui.tweets.TweetListAdapter

/**
 * Updates the data shown in the games [RecyclerView].
 */
@BindingAdapter("gamesListData")
fun bindGamesRecyclerView(recyclerView: RecyclerView, data: List<Game>?) {
    val adapter = recyclerView.adapter as GameListAdapter
    adapter.submitList(data)
}

/**
 * Updates the data shown in the tweets [RecyclerView].
 */
@BindingAdapter("tweetsListData")
fun bindTweetsRecyclerView(recyclerView: RecyclerView, data: List<Post>?) {
    val adapter = recyclerView.adapter as TweetListAdapter
    adapter.submitList(data)
}

/**
 * Updates the data shown in the highlights [RecyclerView].
 */
@BindingAdapter("highlightsListData")
fun bindHighlightRecyclerView(recyclerView: RecyclerView, data: List<Post>?) {
    val adapter = recyclerView.adapter as HighlightListAdapter
    adapter.submitList(data)
}

@BindingAdapter("boxscoreData")
fun bindBoxscoreStats(boxscoreLayout: ConstraintLayout, players: List<Player>?) {
    val fixedPlayerTableLayout = boxscoreLayout.findViewById<TableLayout>(R.id.fixed_player_table_layout)
    if (fixedPlayerTableLayout.childCount == 1) {

    }
}
