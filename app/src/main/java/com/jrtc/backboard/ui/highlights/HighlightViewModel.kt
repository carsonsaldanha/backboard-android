package com.jrtc.backboard.ui.highlights

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HighlightViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is highlights Fragment"
    }
    val text: LiveData<String> = _text
}