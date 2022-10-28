package com.jrtc.backboard.ui.highlights

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrtc.backboard.network.Post
import com.jrtc.backboard.network.RedditApi
import com.jrtc.backboard.network.RedditData
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

    // The internal MutableLiveData that stores a single highlight
    private val _highlight = MutableLiveData<Post>()

    // The external immutable LiveData for a single highlight
    val highlight: LiveData<Post> = _highlight

    /**
     * Gets NBA highlights from the Reddit api Retrofit service and updates the
     * [Post] [List] [LiveData] highlights.
     */
    fun getHighlightsList() {
        viewModelScope.launch {
            val response = RedditApi.retrofitService.getNBAHighlights()
            // Parses the nested JSON object
            response.enqueue(object : Callback<RedditData> {
                override fun onResponse(call: Call<RedditData>, response: Response<RedditData>) {
                    _highlights.value = response.body()?.data?.posts
                }

                override fun onFailure(call: Call<RedditData>, t: Throwable) {
                    _highlights.value = listOf()
                    t.printStackTrace()
                }
            })
        }
    }

    /**
     * Sets [Post] [LiveData] highlight to the passed in tweet to play a highlight when a list item
     * is clicked.
     */
    fun onHighlightClicked(post: Post) {
        _highlight.value = post
    }

}
