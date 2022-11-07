package com.jrtc.backboard.ui.highlights

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.jrtc.backboard.R
import com.jrtc.backboard.databinding.FragmentHighlightsBinding

class HighlightListFragment : Fragment() {

    private val viewModel: HighlightViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHighlightsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // Calls the view model method that calls the Reddit api
        viewModel.getHighlightsList()
        binding.highlightsRecyclerView.adapter = HighlightListAdapter(HighlightListener { highlight ->
            viewModel.onHighlightClicked(highlight)
            findNavController().navigate(R.id.action_navigation_highlights_to_navigation_video)
        })

        // Inflates the layout for this fragment
        return binding.root
    }

}
