package com.dadadadev.nativeweather.features.weather.presentation.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dadadadev.nativeweather.R
import com.dadadadev.nativeweather.features.weather.presentation.WeatherState
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherCard(
    state: WeatherState,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    state.weatherInfo?.currentWeatherData?.let { data ->
        Card(
            colors = CardDefaults.cardColors(
                containerColor = backgroundColor,
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = modifier.padding(16.dp)
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Today "
                            + "${data.time.format(DateTimeFormatter.ofPattern("HH:mm"))}",
                    modifier = modifier.align(Alignment.End),
                    color = Color.White
                )
                Spacer(modifier = modifier.height(32.dp))
                Image(
                    painter = painterResource(id = data.weatherType.iconRes),
                    contentDescription = null,
                    modifier = modifier.height(165.dp)
                )
                Spacer(modifier = modifier.height(16.dp))
                Text(
                    text = "${data.temperatureCelsius}°C",
                    fontSize = 42.sp,
                    color = Color.White
                )
                Spacer(modifier = modifier.height(16.dp))
                Text(
                    text = data.weatherType.weatherDesc,
                    fontSize = 18.sp,
                    color = Color.White
                )
                Spacer(modifier = modifier.height(32.dp))
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherDataDisplay(
                        value = data.pressure.roundToInt(),
                        unit = "hpa",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_pressure),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = Color.White)
                    )
                    WeatherDataDisplay(
                        value = data.humidity.roundToInt(),
                        unit = "%",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = Color.White)
                    )
                    WeatherDataDisplay(
                        value = data.windSpeed.roundToInt(),
                        unit = "km/h",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = Color.White)
                    )
                }
            }
        }
    }
}