package com.jrtc.backboard.ui.highlights

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.jrtc.backboard.R
import com.jrtc.backboard.databinding.FragmentHighlightsBinding
import com.jrtc.backboard.network.StreamableApi
import com.jrtc.backboard.network.StreamableResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * This class defines the fragment for the highlights screen.
 */
class HighlightListFragment : Fragment() {

    private val viewModel: HighlightViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHighlightsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // Uses a two-column layout on larger devices (tablets) and when in landscape orientation
        val gridSpanCount = resources.getInteger(R.integer.grid_span_count)
        val gridLayoutManager = GridLayoutManager(this.context, gridSpanCount)
        binding.highlightsRecyclerView.layoutManager = gridLayoutManager

        // Calls the view model method that calls the Reddit api
        viewModel.getHighlightsList()

        // Inflates the recycler view
        binding.highlightsRecyclerView.adapter =
            HighlightListAdapter(HighlightListener { highlight ->
                viewModel.onHighlightClicked(highlight)
                // Extracts the Streamable video id from the video url
                val highlightVideoURL = highlight.data.url
                val streamableVideoId = highlightVideoURL.substring(23)

                // Calls the Streamable api to get the MP4 video url
                CoroutineScope(Dispatchers.IO).launch {
                    val response = StreamableApi.retrofitService.getVideo(streamableVideoId)
                    // Parses the nested JSON object
                    response.enqueue(object : Callback<StreamableResponse> {
                        override fun onResponse(
                            call: Call<StreamableResponse>,
                            response: Response<StreamableResponse>
                        ) {
                            // Starts the VideoActivity and passes the MP4 video url
                            val mp4VideoUrl = response.body()?.files?.mp4?.url!!
                            val intent = Intent(activity, VideoActivity::class.java)
                            intent.putExtra("mp4VideoUrl", mp4VideoUrl)
                            startActivity(intent)
                        }

                        override fun onFailure(call: Call<StreamableResponse>, t: Throwable) {
                            t.printStackTrace()
                        }
                    })
                }
            })

        return binding.root
    }

}
