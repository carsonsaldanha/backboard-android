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

    private val _children = MutableLiveData<List<Child>>()

    val children: LiveData<List<Child>> = _children

    private val _child = MutableLiveData<Child>()

    val child: LiveData<Child> = _child


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

    fun onHighlightClicked(child: Child) {
        _child.value = child
    }

}


