package com.jrtc.backboard

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jrtc.backboard.network.Child
import com.jrtc.backboard.network.Game
import com.jrtc.backboard.network.Child
import com.jrtc.backboard.ui.games.GameListAdapter
import com.jrtc.backboard.ui.highlights.HighlightListAdapter
import com.jrtc.backboard.ui.tweets.TweetListAdapter

@BindingAdapter("gamesListData")
fun bindGamesRecyclerView(recyclerView: RecyclerView, data: List<Game>?) {
    val adapter = recyclerView.adapter as GameListAdapter
    adapter.submitList(data)
}

@BindingAdapter("tweetListData")
fun bindTweetRecyclerView(recyclerView: RecyclerView, data: List<Child>?) {
    val adapter = recyclerView.adapter as TweetListAdapter
    adapter.submitList(data)
}

@BindingAdapter("highlightlistData")
fun bindHighlightRecyclerView(recyclerView: RecyclerView, data: List<Child>?) {
    val adapter = recyclerView.adapter as HighlightListAdapter
    adapter.submitList(data)
}

fun getTeamDrawableLogo(teamId: Int): Int {
    when (teamId) {
        1610612737 -> return R.drawable.logo_1610612737
        1610612738 -> return R.drawable.logo_1610612738
        1610612739 -> return R.drawable.logo_1610612739
        1610612740 -> return R.drawable.logo_1610612740
        1610612741 -> return R.drawable.logo_1610612741
        1610612742 -> return R.drawable.logo_1610612742
        1610612743 -> return R.drawable.logo_1610612743
        1610612744 -> return R.drawable.logo_1610612744
        1610612745 -> return R.drawable.logo_1610612745
        1610612746 -> return R.drawable.logo_1610612746
        1610612747 -> return R.drawable.logo_1610612747
        1610612748 -> return R.drawable.logo_1610612748
        1610612749 -> return R.drawable.logo_1610612749
        1610612750 -> return R.drawable.logo_1610612750
        1610612751 -> return R.drawable.logo_1610612751
        1610612752 -> return R.drawable.logo_1610612752
        1610612753 -> return R.drawable.logo_1610612753
        1610612754 -> return R.drawable.logo_1610612754
        1610612755 -> return R.drawable.logo_1610612755
        1610612756 -> return R.drawable.logo_1610612756
        1610612757 -> return R.drawable.logo_1610612757
        1610612758 -> return R.drawable.logo_1610612758
        1610612759 -> return R.drawable.logo_1610612759
        1610612760 -> return R.drawable.logo_1610612760
        1610612761 -> return R.drawable.logo_1610612761
        1610612762 -> return R.drawable.logo_1610612762
        1610612763 -> return R.drawable.logo_1610612763
        1610612764 -> return R.drawable.logo_1610612764
        1610612765 -> return R.drawable.logo_1610612765
        1610612766 -> return R.drawable.logo_1610612766
    }
    return R.drawable.logo_nba
}