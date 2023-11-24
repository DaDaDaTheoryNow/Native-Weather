package com.dadadadev.nativeweather.features.weather.domain.repository

import android.location.Location

interface LocationRepository {
    suspend fun getCurrentLocation() : Location?
}