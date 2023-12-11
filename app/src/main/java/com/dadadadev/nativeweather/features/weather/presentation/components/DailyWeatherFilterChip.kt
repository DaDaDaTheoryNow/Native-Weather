package com.dadadadev.nativeweather.features.weather.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DailyWeatherFilterChip(
    modifier: Modifier = Modifier,
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        modifier = modifier.animateContentSize(
            animationSpec = tween(durationMillis = 750,
                easing = LinearOutSlowInEasing)),
        colors = ChipDefaults.filterChipColors(
            backgroundColor = MaterialTheme.colorScheme.onPrimary,
        ),
        selected = isSelected,
        selectedIcon = {
            Icon(
                imageVector = Icons.Filled.Done,
                tint = Color.White,
                contentDescription = "Done icon",
                modifier = Modifier.size(FilterChipDefaults.IconSize)
            )
        },
        onClick = onClick,
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            color = Color.White,
        )
    }
}