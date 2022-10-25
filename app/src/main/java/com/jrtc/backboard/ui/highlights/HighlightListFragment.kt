package com.jrtc.backboard.ui.highlights

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jrtc.backboard.databinding.FragmentHighlightsBinding

class HighlightListFragment : Fragment() {

    private var _binding: FragmentHighlightsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val highlightViewModel = ViewModelProvider(this).get(HighlightViewModel::class.java)

        _binding = FragmentHighlightsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHighlights
        highlightViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}