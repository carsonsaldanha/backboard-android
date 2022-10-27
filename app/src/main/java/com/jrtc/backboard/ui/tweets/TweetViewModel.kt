package com.jrtc.backboard.ui.tweets

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrtc.backboard.network.Child
import com.jrtc.backboard.network.RedditApi
import com.jrtc.backboard.network.RedditData
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TweetViewModel : ViewModel() {
    // Internal MutableLiveData that stores the list of game objects
    private val _tweets = MutableLiveData<List<Child>>()

    // External immutable LiveData for the list of games objects
    val tweets: LiveData<List<Child>> = _tweets

    fun getListOfTweets() {
        viewModelScope.launch {
            try {
                val response = RedditApi.retrofitService.getNBATweets()
                response.enqueue(object : Callback<RedditData> {
                    override fun onResponse(
                        call: Call<RedditData>,
                        response: Response<RedditData>
                    ) {
                        _tweets.value = response.body()?.data?.children
                    }

                    override fun onFailure(call: Call<RedditData>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
            } catch (e: Exception) {
                _tweets.value = listOf()
                Log.v("error", "Failure: ${e.message}")
            }
        }
    }
}