package com.jrtc.backboard.ui.games

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrtc.backboard.network.Boxscore
import com.jrtc.backboard.network.Game
import com.jrtc.backboard.network.NBAApi
import com.jrtc.backboard.network.TodaysGames
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * The [ViewModel] that is attached to the [GameViewModel].
 */
class GameViewModel : ViewModel() {

    // The internal MutableLiveData that stores the list of games
    private val _games = MutableLiveData<List<Game>>()

    // The external immutable LiveData for the list of games
    val games: LiveData<List<Game>> = _games

    // The internal MutableLiveData that stores a single game
    private val _game = MutableLiveData<Game>()

    // The external immutable LiveData for a single game
    val game: LiveData<Game> = _game

    /**
     * Gets NBA games information from the NBA api Retrofit service and updates the
     * [Game] [List] [LiveData].
     */
    fun getGamesList() {
        viewModelScope.launch {
            val response = NBAApi.retrofitService.getGames()
            // Parses the nested JSON object
            response.enqueue(object : Callback<TodaysGames> {
                override fun onResponse(call: Call<TodaysGames>, response: Response<TodaysGames>) {
                    _games.value = response.body()?.scoreboard?.games
                }

                override fun onFailure(call: Call<TodaysGames>, t: Throwable) {
                    _games.value = listOf()
                    t.printStackTrace()
                }
            })
        }
    }

    private fun getBoxscore() {
        viewModelScope.launch {
            val response = NBAApi.retrofitService.getBoxscore(_game.value!!.gameId)
            // Parses the nested JSON object
            response.enqueue(object : Callback<Boxscore> {
                override fun onResponse(call: Call<Boxscore>, response: Response<Boxscore>) {
                    _game.value = response.body()?.game
                    Log.v("PLAYER", _game.value!!.homeTeam.players!![0].nameI)
                }

                override fun onFailure(call: Call<Boxscore>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }

    /**
     * Sets [Game] [LiveData] to the passed in game to display the details of a game when a list
     * item is clicked.
     */
    fun onGameClicked(game: Game) {
        _game.value = game
        getBoxscore()
    }

}
