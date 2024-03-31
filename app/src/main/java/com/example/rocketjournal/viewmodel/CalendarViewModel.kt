package com.example.rocketjournal.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
class CalendarViewModel : ViewModel() {
    var month = mutableStateOf(LocalDate.now().month)
    val today = LocalDate.now()
    var shownDate = mutableStateOf(today)
}