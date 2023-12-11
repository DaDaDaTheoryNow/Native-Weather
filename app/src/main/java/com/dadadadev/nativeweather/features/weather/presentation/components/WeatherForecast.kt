package com.dadadadev.nativeweather.features.weather.presentation.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dadadadev.nativeweather.common.util.getDayOfWeekExpression
import com.dadadadev.nativeweather.features.weather.presentation.WeatherState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherForecast(
    state: WeatherState,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    onFilterChipClicked: (Int) -> Unit
) {
    state.weatherInfo?.weatherDataPerDay?.get(state.currentDay)?.let { data ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                for (i in 0..6) {
                    val dayOfWeek = state.weatherInfo.weatherDataPerDay[i]?.get(0)?.time?.dayOfWeek
                    val isSelected = state.selectedFilterChipStates.getOrElse(i) { false }
                    DailyWeatherFilterChip(
                        modifier = modifier.padding(4.dp),
                        title = if (i == 0) {
                            "Today"
                        } else {
                            getDayOfWeekExpression(dayOfWeek!!)
                        },
                        isSelected = isSelected,
                        onClick = { onFilterChipClicked(i) }
                    )
                }
            }
            Divider(
                modifier = modifier.padding(bottom = 20.dp),
                color = MaterialTheme.colorScheme.surfaceTint
            )
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
            ) {
                data.forEach { weatherData ->
                    HourlyWeatherDisplay(
                        weatherData = weatherData,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
            }
            Spacer(modifier = modifier.height(20.dp))
        }
    }
}