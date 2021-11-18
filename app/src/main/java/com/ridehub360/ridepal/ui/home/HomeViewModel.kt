package com.ridehub360.ridepal.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Hello &#x1F44B; Welcome to the DashBoard &#x1F917;"
    }
    val text: LiveData<String> = _text
}