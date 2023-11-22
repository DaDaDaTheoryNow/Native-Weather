package com.dadadadev.nativeweather.presentation

import com.dadadadev.nativeweather.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
