package com.ridehub360.ridepal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {

    var emailValid = MutableLiveData<Boolean>()
    var passwordValid = MutableLiveData<Boolean>()
    var allDataFilled = MutableLiveData<Boolean>()

    fun setDataFilled() {
        allDataFilled.value = emailValid.value == true && passwordValid.value == true
    }
}