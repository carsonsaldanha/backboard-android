package com.jrtc.backboard.ui.games

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.transition.MaterialContainerTransform
import com.jrtc.backboard.R
import com.jrtc.backboard.databinding.FragmentBoxscoreBinding
import com.jrtc.backboard.network.getTeamDrawableLogo

/**
 * This class defines the fragment for the boxscore screen.
 */
class BoxscoreFragment : Fragment() {

    private val viewModel: GameViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Games to Boxscore transitions
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment_activity_main
            duration = 300
            scrimColor = Color.TRANSPARENT

            val typedValue = TypedValue()
            context?.theme?.resolveAttribute(
                com.google.android.material.R.attr.colorSurface,
                typedValue,
                true
            )
            val color = ContextCompat.getColor(requireContext(), typedValue.resourceId)
            setAllContainerColors(color)
        }
    }

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
