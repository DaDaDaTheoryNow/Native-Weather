package com.dadadadev.nativeweather.domain.location

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation() : Location?
}