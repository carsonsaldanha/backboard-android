package com.jrtc.backboard.ui.highlights

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.jrtc.backboard.databinding.FragmentVideoBinding

class VideoFragment : Fragment() {

    private val viewModel: HighlightViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentVideoBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val highlightVideoURL = viewModel.highlight.value?.data?.url
        val streamableVideoId = highlightVideoURL?.subSequence(23, highlightVideoURL.length)
        val videoHTML = "<html>\n" +
                "  <head>\n" +
                "    <style type=\"text/css\">\n" +
                "      html,\n" +
                "      body {\n" +
                "        height: 100%;\n" +
                "        width: 100%;\n" +
                "        margin: 0;\n" +
                "        padding: 0;\n" +
                "      }\n" +
                "\n" +
                "      .frame {\n" +
                "        position: relative;\n" +
                "        width: 100%;\n" +
                "        height: 0;\n" +
                "        padding-bottom: 56.25%;\n" +
                "      }\n" +
                "\n" +
                "      iframe {\n" +
                "        position: absolute;\n" +
                "        top: 0;\n" +
                "        left: 0;\n" +
                "        overflow: hidden;\n" +
                "      }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div class='frame'>\n" +
                "      <iframe src=\"https://streamable.com/e/$streamableVideoId\" width='100%' height='100%' allowfullscreen frameborder='0' scrolling='no'></iframe>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>"
        binding.videoWebView.settings.javaScriptEnabled = true
        binding.videoWebView.loadData(videoHTML, "text/html", null)

        // Inflates the layout for this fragment
        return binding.root
    }

}