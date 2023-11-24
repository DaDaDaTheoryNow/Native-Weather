package com.dadadadev.nativeweather.features.weather.data.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.dadadadev.nativeweather.features.weather.data.mappers.toWeatherInfo
import com.dadadadev.nativeweather.features.weather.data.remote.WeatherApi
import com.dadadadev.nativeweather.features.weather.domain.repository.WeatherRepository
import com.dadadadev.nativeweather.common.util.Resource
import com.dadadadev.nativeweather.features.weather.domain.weather.WeatherInfo
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            val weatherInfo = api.getWeatherData(lat, long).toWeatherInfo()
            Resource.Success(weatherInfo)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}