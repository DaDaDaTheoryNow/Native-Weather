package com.dadadadev.nativeweather.features.weather.presentation

import com.dadadadev.nativeweather.features.weather.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isRefreshing: Boolean = false
)
