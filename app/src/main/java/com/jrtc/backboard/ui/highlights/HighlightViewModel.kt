package com.jrtc.backboard.ui.highlights

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrtc.backboard.network.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HighlightViewModel : ViewModel() {

    // The internal MutableLiveData that stores the list of children objects
    private val _children = MutableLiveData<List<Child>>()

    // The external immutable LiveData for the list of children objects
    val children: LiveData<List<Child>> = _children


    fun getNBAHighlightsList() {
        viewModelScope.launch {
            try {
                val response = RedditApi.retrofitService.getNBAHighlights()
                response.enqueue(object : Callback<RedditData> {
                    override fun onResponse(
                        call: Call<RedditData>,
                        response: Response<RedditData>
                    ) {
                        _children.value = response.body()?.data?.children
                    }

                    override fun onFailure(call: Call<RedditData>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
            } catch (e: Exception) {
                _children.value = listOf()
                Log.v("error", "Failure: ${e.message}")
            }
        }
    }

}


