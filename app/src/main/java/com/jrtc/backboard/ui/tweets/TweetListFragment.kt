package com.jrtc.backboard.ui.tweets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jrtc.backboard.databinding.FragmentTweetsBinding

class TweetListFragment : Fragment() {

    private var _binding: FragmentTweetsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val tweetViewModel = ViewModelProvider(this).get(TweetViewModel::class.java)

        _binding = FragmentTweetsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textTweets
        tweetViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}