package com.ridehub360.ridepal.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ridehub360.ridepal.model.DestinationModel
import com.ridehub360.ridepal.model.LocationModel
import com.ridehub360.ridepal.model.ReadingsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class HomeViewModel: ViewModel() {
    private var liveData = MutableLiveData<List<DestinationModel>>()
    var _liveData: LiveData<List<DestinationModel>> = liveData

    private var liveData1 = MutableLiveData<List<ReadingsModel>>()
    var _liveData1: LiveData<List<ReadingsModel>> = liveData1

    init {
        liveData.value = dummyData()
        liveData1.value = dummyData2()
    }

   /* fun getLocation(): LiveData<List<DestinationModel>> {
        liveData.value = dummyData()
        return _liveData
    }*/
}

private fun dummyData(): List<DestinationModel> {
    return mutableListOf(
        DestinationModel(
            LocationModel("Lagos"),
            "1hr 10mins", "5km"
        ), DestinationModel(
            LocationModel("Benin"),
            "2hr 30mins", "15km"
        ), DestinationModel(
            LocationModel("Sapele"),
            "1hr 00mins", "1km"
        )
    )
}
private fun dummyData2(): List<ReadingsModel> {
    return mutableListOf(
        ReadingsModel("Tuesday: December 10, 1999",
        "5:11:56","45 BPM",67), ReadingsModel("Sunday: September 10, 2004",
            "5:41:16","455 BPM",88), ReadingsModel("Monday: April 11, 1989",
            "4:11:96","145 BPM",96))
}