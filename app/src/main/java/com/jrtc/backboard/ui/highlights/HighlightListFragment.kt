package com.jrtc.backboard.ui.highlights

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.jrtc.backboard.databinding.FragmentGamesBinding
import com.jrtc.backboard.databinding.FragmentHighlightsBinding
import com.jrtc.backboard.ui.games.GameListAdapter
import com.jrtc.backboard.ui.games.GameListener
import com.jrtc.backboard.ui.games.GameViewModel

class HighlightListFragment : Fragment() {

    private val viewModel: HighlightViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentGamesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        // binding.viewModel = viewModel

        // Calls the view model method that calls the amphibians api
        viewModel.getNBAHighlightsList()
        binding.gamesRecyclerView.adapter = GameListAdapter(GameListener { game ->
//            viewModel.onGameClicked(game)
//            findNavController()
//                .navigate(R.id.action_amphibianListFragment_to_amphibianDetailFragment)
        })

        // Inflate the layout for this fragment
        return binding.root

    }
}