package com.ridehub360.ridepal.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ridehub360.ridepal.model.ReadingsModel

class ReadingsViewModel : ViewModel() {
    private var liveData1 = MutableLiveData<List<ReadingsModel>>()
    var _liveData1: LiveData<List<ReadingsModel>> = liveData1

    init {
        liveData1.value = dummyData2()
    }

    private fun dummyData2(): List<ReadingsModel> {
        return mutableListOf(
            ReadingsModel("Tuesday: December 10, 1999",
                "5:11:56","45 BPM",67), ReadingsModel("Sunday: September 10, 2004",
                "5:41:16","455 BPM",88), ReadingsModel("Monday: April 11, 1989",
                "4:11:96","145 BPM",96))
    }
}