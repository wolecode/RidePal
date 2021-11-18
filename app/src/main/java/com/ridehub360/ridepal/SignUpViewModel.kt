package com.ridehub360.ridepal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    var nameValid = MutableLiveData<Boolean>()
    var emailValid = MutableLiveData<Boolean>()
    var passwordValid = MutableLiveData<Boolean>()
    var confirmPasswordValid = MutableLiveData<Boolean>()
    var allDataFilled = MutableLiveData<Boolean>()

    fun setDataFilled() {
        allDataFilled.value = nameValid.value == true && emailValid.value == true && passwordValid.value == true && confirmPasswordValid.value == true
    }

}