package com.jrtc.backboard.ui.tweets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.jrtc.backboard.R
import com.jrtc.backboard.databinding.FragmentTweetsBinding

/**
 * This class defines the fragment for the tweets screen.
 */
class TweetListFragment : Fragment() {

    private val viewModel: TweetViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTweetsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // Uses a two-column layout on larger devices (tablets) and when in landscape orientation
        val gridSpanCount = resources.getInteger(R.integer.grid_span_count)
        val gridLayoutManager = GridLayoutManager(this.context, gridSpanCount)
        binding.tweetsRecyclerView.layoutManager = gridLayoutManager

        // Calls the view model method that calls the Reddit api
        viewModel.getTweetsList()

        // Inflates the recycler view
        binding.tweetsRecyclerView.adapter = TweetListAdapter(TweetListener { tweet ->
            viewModel.onTweetClicked(tweet)
        })

        return binding.root
    }

}
