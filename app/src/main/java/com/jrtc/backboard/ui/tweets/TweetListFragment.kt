package com.jrtc.backboard.ui.tweets

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

        // Calls the view model method that calls the Reddit api
        viewModel.getTweetsList()

        // Inflates the recycler view
        binding.tweetsRecyclerView.adapter = TweetListAdapter(TweetListener { tweet ->
            viewModel.onTweetClicked(tweet)
        })

        return binding.root
    }

}
