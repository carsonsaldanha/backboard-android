package com.jrtc.backboard.ui.games

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

class GameViewModel : ViewModel() {

    // The internal MutableLiveData that stores the list of games objects
    private val _games = MutableLiveData<List<Game>>()

    // The external immutable LiveData for the list of games objects
    val games: LiveData<List<Game>> = _games

    // The internal MutableLiveData that stores a single game object
    // Used to display the details of an game when a list item is clicked
    private val _game = MutableLiveData<Game>()

    // The external immutable LiveData for a single game object
    // Used to display the details of an game when a list item is clicked
    val game: LiveData<Game> = _game

    fun getNBAGamesList() {
        viewModelScope.launch {
            try {
                val response = NBAApi.retrofitService.getNBAGames()
                response.enqueue(object : Callback<TodaysGames> {
                    override fun onResponse(
                        call: Call<TodaysGames>,
                        response: Response<TodaysGames>
                    ) {
                        _games.value = response.body()?.scoreboard?.games
                    }

                    override fun onFailure(call: Call<TodaysGames>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
            } catch (e: Exception) {
                _games.value = listOf()
                Log.v("error", "Failure: ${e.message}")
            }
        }
    }

    fun onGameClicked(game: Game) {
        _game.value = game
    }

}