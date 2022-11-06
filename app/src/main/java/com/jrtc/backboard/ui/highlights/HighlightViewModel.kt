package com.jrtc.backboard.ui.highlights

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrtc.backboard.network.Post
import com.jrtc.backboard.network.RedditApi
import com.jrtc.backboard.network.RedditResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * The [ViewModel] that is attached to the [HighlightViewModel].
 */
class HighlightViewModel : ViewModel() {

    // The internal MutableLiveData that stores the list of highlights
    private val _highlights = MutableLiveData<List<Post>>()

    // The external immutable LiveData for the list of highlights
    val highlights: LiveData<List<Post>> = _highlights

    /**
     * Gets NBA highlights from the Reddit api Retrofit service and updates the
     * [Post] [List] [LiveData] highlights.
     */
    fun getHighlightsList() {
        viewModelScope.launch {
            val response = RedditApi.retrofitService.getNBAHighlights()
            // Parses the nested JSON object
            response.enqueue(object : Callback<RedditResponse> {
                override fun onResponse(
                    call: Call<RedditResponse>,
                    response: Response<RedditResponse>
                ) {
                    _highlights.value = response.body()?.data?.posts
                }

                override fun onFailure(call: Call<RedditResponse>, t: Throwable) {
                    _highlights.value = listOf()
                    t.printStackTrace()
                }
            })
        }
    }

}
