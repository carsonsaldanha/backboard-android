package com.jrtc.backboard.ui.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.jrtc.backboard.R
import com.jrtc.backboard.databinding.FragmentGamesBinding

/**
 * This class defines the fragment for the games screen.
 */
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

        // Uses a two-column layout on larger devices (tablets) and when in landscape orientation
        val gridSpanCount = resources.getInteger(R.integer.grid_span_count)
        val gridLayoutManager = GridLayoutManager(context, gridSpanCount)
        binding.gamesRecyclerView.layoutManager = gridLayoutManager

        // Calls the view model method that calls the NBA api
        viewModel.getGamesList()

        // Displays the "No games scheduled" text if there are no games today
        viewModel.games.observe(viewLifecycleOwner) { value ->
            if (value.isEmpty()) {
                binding.noGamesScheduledTextView.visibility = View.VISIBLE
            }
        }

        // Inflates the recycler view
        binding.gamesRecyclerView.adapter = GameListAdapter(GameListener { cardView, game ->
            // Only displays the boxscore if the game has started or finished
            if (game.gameStatus != 1) {
                viewModel.onGameClicked(game)

                // Applies transitions and navigates to the boxscore fragment
                val gameCardDetailTransitionName =
                    getString(R.string.game_card_detail_transition_name)
                val extras = FragmentNavigatorExtras(cardView to gameCardDetailTransitionName)
                findNavController().navigate(
                    R.id.action_navigation_games_to_navigation_boxscore,
                    null,
                    null,
                    extras
                )
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Return transition from boxscore fragment
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }

}
