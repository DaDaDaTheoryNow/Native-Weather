package com.dadadadev.nativeweather.features.weather.domain.weather

import java.time.LocalDateTime

data class WeatherData(
    // to show a period of time
    val minTime: LocalDateTime, // example: 16:30
    val time: LocalDateTime, // example: 17:00

    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val weatherType: WeatherType
)