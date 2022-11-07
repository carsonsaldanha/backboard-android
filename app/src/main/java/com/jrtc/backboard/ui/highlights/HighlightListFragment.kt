package com.jrtc.backboard.ui.highlights

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.jrtc.backboard.databinding.FragmentHighlightsBinding
import com.jrtc.backboard.network.StreamableApi
import com.jrtc.backboard.network.StreamableResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

        // Calls the view model method that calls the Reddit api
        viewModel.getHighlightsList()
        binding.highlightsRecyclerView.adapter = HighlightListAdapter(HighlightListener { highlight ->
            viewModel.onHighlightClicked(highlight)
            val highlightVideoURL = highlight.data.url
            val streamableVideoId = highlightVideoURL.substring(23)

            CoroutineScope(Dispatchers.IO).launch {
                val response = StreamableApi.retrofitService.getVideo(streamableVideoId)
                // Parses the nested JSON object
                response.enqueue(object : Callback<StreamableResponse> {
                    override fun onResponse(call: Call<StreamableResponse>, response: Response<StreamableResponse>) {
                        val mp4VideoUrl = response.body()?.files?.mp4?.url!!
//                        val mp4MobileVideoUrl = response.body()?.files?.mp4Mobile?.url!!
                        val intent = Intent(activity, VideoActivity::class.java)
                        intent.putExtra("mp4VideoUrl", mp4VideoUrl)
//                        intent.putExtra("mp4MobileVideoUrl", mp4MobileVideoUrl)
                        startActivity(intent)
                    }

                    override fun onFailure(call: Call<StreamableResponse>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
            }
        })

        // Inflates the layout for this fragment
        return binding.root
    }

}
