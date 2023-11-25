package com.dadadadev.nativeweather.features.weather.data.mappers

import android.app.Application
import android.location.Geocoder
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import com.dadadadev.nativeweather.features.weather.data.remote.WeatherDataDto
import com.dadadadev.nativeweather.features.weather.data.remote.WeatherDto
import com.dadadadev.nativeweather.features.weather.domain.weather.WeatherData
import com.dadadadev.nativeweather.features.weather.domain.weather.WeatherInfo
import com.dadadadev.nativeweather.features.weather.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]

        val parsedTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME)

        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = parsedTime,
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues { listEntry ->
        listEntry.value.map { it.data }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDto.toWeatherInfo() : WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        var hour = if(now.minute < 30) now.hour else now.hour + 1
        if (hour == 24) {
            hour = 0
        }

        it.time.hour == hour
    }

    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}