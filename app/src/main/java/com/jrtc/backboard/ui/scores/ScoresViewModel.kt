package com.jrtc.backboard.ui.scores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrtc.backboard.network.NBAApi
import kotlinx.coroutines.launch

class ScoresViewModel : ViewModel() {

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    init {
        getNBAGames()
    }

    private fun getNBAGames() {
        viewModelScope.launch {
            try {
                val listResult = NBAApi.retrofitService.getNBAGames()
                _text.value = listResult.toString()
            } catch (e: Exception) {
                _text.value = "Failure: ${e.message}"
            }
        }
    }

}