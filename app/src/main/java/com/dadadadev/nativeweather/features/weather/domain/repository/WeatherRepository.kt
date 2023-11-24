package com.dadadadev.nativeweather.features.weather.domain.repository

import com.dadadadev.nativeweather.common.util.Resource
import com.dadadadev.nativeweather.features.weather.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}