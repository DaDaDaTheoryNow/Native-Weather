package com.dadadadev.nativeweather.features.weather.domain.repository

import android.location.Address
import android.location.Location

interface LocationRepository {
    suspend fun getCurrentLocation() : Location?
     fun getCurrentAddress(lat: Double, long: Double) : Address?
}