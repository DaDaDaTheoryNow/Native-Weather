package com.dadadadev.nativeweather.features.weather.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dadadadev.nativeweather.features.weather.presentation.components.WeatherCard
import com.dadadadev.nativeweather.features.weather.presentation.components.WeatherForecast

@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel,
    modifier: Modifier = Modifier
) {
    val pullRefreshState = rememberPullRefreshState(viewModel.state.isRefreshing, { viewModel.loadWeatherInfo() })

    Box(
        modifier = modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
        ) {
            WeatherCard(
                state = viewModel.state,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer
            )
            Spacer(modifier = modifier.height(10.dp))
            WeatherForecast(
                state = viewModel.state,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                onFilterChipClicked = { viewModel.selectDayOfWeek(it) }
            )
        }
        if(viewModel.state.isLoading) {
            CircularProgressIndicator(
                modifier = modifier.align(Alignment.Center)
            )
        }
        viewModel.state.error?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = modifier.align(Alignment.Center)
            )
        }
        PullRefreshIndicator(
            refreshing = viewModel.state.isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            contentColor = MaterialTheme.colorScheme.onSecondary,
            backgroundColor = MaterialTheme.colorScheme.secondary
        )
    }
}