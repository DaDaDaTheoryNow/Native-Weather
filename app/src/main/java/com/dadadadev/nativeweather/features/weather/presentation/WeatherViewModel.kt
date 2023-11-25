package com.dadadadev.nativeweather.features.weather.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dadadadev.nativeweather.features.weather.domain.repository.WeatherRepository
import com.dadadadev.nativeweather.common.util.Resource
import com.dadadadev.nativeweather.features.weather.domain.repository.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository
): ViewModel() {
    var state by mutableStateOf(WeatherState())
        private set

    fun selectDayOfWeek(i: Int) {
        state = state.copy(
            selectedFilterChipStates = List(7) { index -> index == i }
        )
    }

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(
                weatherInfo = null,
                isLoading = true,
                error = null
            )
            locationRepository.getCurrentLocation()?.let { location ->
                // find location area by lat and lon
                val address = locationRepository.getCurrentAddress(location.latitude, location.longitude)
                address?.let {
                    val cityName = address.locality ?: address.subAdminArea ?: address.adminArea
                    state = state.copy(
                        locality = cityName ?: "Unknown"
                    )
                }

                when(val result = weatherRepository.getWeatherData(location.latitude, location.longitude)) {
                    is Resource.Success -> {
                        state = state.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            } ?: kotlin.run {
                state = state.copy(
                    weatherInfo = null,
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                )
            }
        }
    }
}