package com.example.rocketjournal.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.YearMonth


@RequiresApi(Build.VERSION_CODES.O)
class CalendarViewModel : ViewModel() {
    val today = LocalDate.now()
    var month = mutableStateOf(YearMonth.from(today))
    var shownDate = mutableStateOf(today)
}