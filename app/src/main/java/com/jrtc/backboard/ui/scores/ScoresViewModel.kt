package com.jrtc.backboard.ui.scores

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrtc.backboard.network.Game
import com.jrtc.backboard.network.NBAApi
import com.jrtc.backboard.network.TodaysGames
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScoresViewModel : ViewModel() {

    // The internal MutableLiveData that stores the list of amphibian objects
    private val _games = MutableLiveData<List<Game>>()

    // The external immutable LiveData for the list of amphibian objects
    val games: LiveData<List<Game>> = _games

    fun getNBAGamesList() {
        viewModelScope.launch {
            try {
                val response = NBAApi.retrofitService.getNBAGames()
                response.enqueue(object: Callback<TodaysGames> {
                    override fun onResponse(call: Call<TodaysGames>, response: Response<TodaysGames>) {
                        _games.value = response.body()?.scoreboard?.games
                    }

                    override fun onFailure(call: Call<TodaysGames>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
            } catch (e: Exception) {
                Log.v("error", "Failure: ${e.message}")
            }
        }
    }

}