package com.example.customer_citydriver.Views.HomeScreen

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import android.location.LocationManager
import androidx.lifecycle.MutableLiveData
import com.mapbox.common.location.Location

class LocationViewModel(application: Application): AndroidViewModel(application) {

    private val _isGPSEnabled = MutableLiveData<Boolean>()

    val isGPSEnabled: LiveData<Boolean> = _isGPSEnabled

    fun checkGPSStatus() {
        val locationManager = getApplication<Application>().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        _isGPSEnabled.value = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }




}
