package com.example.rocketjournal.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters


@RequiresApi(Build.VERSION_CODES.O)
class CalendarViewModel : ViewModel() {
    // data for monthly view
    val today = LocalDate.now()
    var month = mutableStateOf(YearMonth.from(today))
    var shownDate = mutableStateOf(today)

    // data for weekly view
    var currentWeekStart = mutableStateOf(today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)))
    var currentWeekEnd = mutableStateOf(currentWeekStart.value.plusDays(6))

}