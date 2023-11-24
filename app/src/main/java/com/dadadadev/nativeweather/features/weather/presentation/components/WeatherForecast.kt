package com.dadadadev.nativeweather.features.weather.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dadadadev.nativeweather.features.weather.presentation.WeatherState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherForecast(
    state: WeatherState,
    modifier: Modifier = Modifier,
    backgroundColor: Color
) {
    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { data ->

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
        ) {
            Text(
                modifier = modifier.padding(start = 16.dp, top = 4.dp),
                text = "Today",
                fontSize = 20.sp,
                color = Color.White
            )
            Divider(
                modifier = modifier.padding(top = 5.dp, bottom = 20.dp),
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