package com.dadadadev.nativeweather.features.weather.domain.repository

import android.location.Address
import android.location.Location
import retrofit2.http.Query

interface LocationRepository {
    suspend fun getCurrentLocation() : Location?
     fun getCurrentAddress(lat: Double, long: Double) : Address?
}