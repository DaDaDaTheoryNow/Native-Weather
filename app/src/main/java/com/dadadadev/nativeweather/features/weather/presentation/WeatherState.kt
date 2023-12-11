package com.dadadadev.nativeweather.features.weather.presentation

import com.dadadadev.nativeweather.features.weather.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val currentDay: Int = 0,
    // first value is true, because it's today
    var selectedFilterChipStates: List<Boolean> = listOf(true, false, false, false, false, false, false),
    val locality: String = "Unknown",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isRefreshing: Boolean = false
)
