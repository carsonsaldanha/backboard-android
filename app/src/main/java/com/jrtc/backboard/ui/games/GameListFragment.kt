package com.jrtc.backboard.ui.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.jrtc.backboard.databinding.FragmentGamesBinding

class GameListFragment : Fragment() {

    private val viewModel: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentGamesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // Calls the view model method that calls the amphibians api
        viewModel.getNBAGamesList()
        binding.gamesRecyclerView.adapter = GameListAdapter(GameListener { game ->
            viewModel.onGameClicked(game)
//            findNavController()
//                .navigate(R.id.action_amphibianListFragment_to_amphibianDetailFragment)
        })

        // Inflate the layout for this fragment
        return binding.root

    }

}