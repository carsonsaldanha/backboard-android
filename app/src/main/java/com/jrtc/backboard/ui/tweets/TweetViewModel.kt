package com.jrtc.backboard.ui.tweets

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
 * The [ViewModel] that is attached to the [TweetViewModel].
 */
class TweetViewModel : ViewModel() {

    // The internal MutableLiveData that stores the list of tweets
    private val _tweets = MutableLiveData<List<Post>>()

    // The external immutable LiveData for the list of tweets
    val tweets: LiveData<List<Post>> = _tweets

    // The internal MutableLiveData that stores a single tweet
    private val _tweet = MutableLiveData<Post>()

    // The external immutable LiveData for a single tweet
    val tweet: LiveData<Post> = _tweet

    /**
     * Gets NBA tweets from the Reddit api Retrofit service and updates the
     * [Post] [List] [LiveData] tweets.
     */
    fun getTweetsList() {
        viewModelScope.launch {
            val response = RedditApi.retrofitService.getNBATweets()
            // Parses the nested JSON object
            response.enqueue(object : Callback<RedditData> {
                override fun onResponse(call: Call<RedditData>, response: Response<RedditData>) {
                    _tweets.value = response.body()?.data?.posts
                }

                override fun onFailure(call: Call<RedditData>, t: Throwable) {
                    _tweets.value = listOf()
                    t.printStackTrace()
                }
            })
        }
    }

    /**
     * Sets [Post] [LiveData] tweet to the passed in tweet to open a tweet when a list item is
     * clicked.
     */
    fun onTweetClicked(tweet: Post) {
        _tweet.value = tweet
    }

}
