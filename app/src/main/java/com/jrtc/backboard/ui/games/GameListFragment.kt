package com.jrtc.backboard.ui.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.jrtc.backboard.R
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

        // Calls the view model method that calls the NBA api
        viewModel.getGamesList()
        binding.gamesRecyclerView.adapter = GameListAdapter(GameListener { game ->
            viewModel.onGameClicked(game)
            // Only displays the boxscore if the game has started or finished
            if (game.gameStatus != 1) {
                findNavController().navigate(R.id.action_navigation_games_to_navigation_boxscore)
            }
        })

        // Inflates the layout for this fragment
        return binding.root
    }

}
