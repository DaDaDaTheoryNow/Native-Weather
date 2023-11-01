package com.dadadadev.nativeweather.domain.repository

import com.dadadadev.nativeweather.domain.util.Resource
import com.dadadadev.nativeweather.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}