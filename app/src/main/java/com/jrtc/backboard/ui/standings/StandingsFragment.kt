package com.jrtc.backboard.ui.standings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jrtc.backboard.databinding.FragmentStandingsBinding

class StandingsFragment : Fragment() {

    private var _binding: FragmentStandingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val standingsViewModel = ViewModelProvider(this).get(StandingsViewModel::class.java)

        _binding = FragmentStandingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textStandings
        standingsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}