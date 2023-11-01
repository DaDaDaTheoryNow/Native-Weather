package com.dadadadev.nativeweather.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.dadadadev.nativeweather.data.mappers.toWeatherInfo
import com.dadadadev.nativeweather.data.remote.WeatherApi
import com.dadadadev.nativeweather.domain.repository.WeatherRepository
import com.dadadadev.nativeweather.domain.util.Resource
import com.dadadadev.nativeweather.domain.weather.WeatherInfo
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