package com.dadadadev.nativeweather.common.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.DayOfWeek

@RequiresApi(Build.VERSION_CODES.O)
fun getDayOfWeekExpression(dayOfWeek: DayOfWeek): String {
    return when (dayOfWeek) {
        DayOfWeek.MONDAY -> "Monday"
        DayOfWeek.TUESDAY -> "Tuesday"
        DayOfWeek.WEDNESDAY -> "Wednesday"
        DayOfWeek.THURSDAY -> "Thursday"
        DayOfWeek.FRIDAY -> "Friday"
        DayOfWeek.SATURDAY -> "Saturday"
        DayOfWeek.SUNDAY -> "Sunday"
    }
}