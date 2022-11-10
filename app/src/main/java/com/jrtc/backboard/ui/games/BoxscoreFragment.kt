package com.jrtc.backboard.ui.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.jrtc.backboard.databinding.FragmentBoxscoreBinding
import com.jrtc.backboard.network.getTeamDrawableLogo

/**
 * This class defines the fragment for the boxscore screen.
 */
class BoxscoreFragment : Fragment() {

    private val viewModel: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBoxscoreBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.awayTeamLogoImageView.setImageResource(getTeamDrawableLogo(viewModel.game.value!!.awayTeam.teamId))
        binding.homeTeamLogoImageView.setImageResource(getTeamDrawableLogo(viewModel.game.value!!.homeTeam.teamId))

        return binding.root
    }

}
