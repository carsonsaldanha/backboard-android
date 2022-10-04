package com.jrtc.backboard.ui.scores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jrtc.backboard.databinding.FragmentScoresBinding

class ScoresFragment : Fragment() {

    private var _binding: FragmentScoresBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val scoresViewModel =
                ViewModelProvider(this).get(ScoresViewModel::class.java)

        _binding = FragmentScoresBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textScores
        scoresViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}