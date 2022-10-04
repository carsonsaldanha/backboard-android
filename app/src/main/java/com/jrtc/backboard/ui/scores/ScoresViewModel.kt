package com.jrtc.backboard.ui.scores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoresViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is scores Fragment"
    }
    val text: LiveData<String> = _text
}