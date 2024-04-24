package com.example.test

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rocketjournal.model.dataModel.JournalEntryData


import com.example.rocketjournal.viewmodel.CalendarViewModel
import com.example.rocketjournal.viewmodel.JournalEntryViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen(navController: NavController, viewModel: CalendarViewModel = hiltViewModel(), JEViewModel: JournalEntryViewModel = hiltViewModel()) {
    val primaryColor = Color(0xFF606BD1)
    val secondaryColor = Color(0xFFBA355D)
    val selectedColor = Color(0xFFB98231)
    val journalsState = JEViewModel.entryList.collectAsState(initial = emptyList()).value ?: emptyList()
    val sortedJournals = journalsState.sortedWith(LocalDateTimeComparator())

    Column(modifier = Modifier
        .background(primaryColor)
        .fillMaxSize()
        .drawBehind {
            drawCircle(
                color = secondaryColor,
                radius = 700f,
                center = Offset(size.width / 2, size.height / 6)
            )
        }
    ) {
        Column (modifier = Modifier
            .fillMaxSize()) {
            Row(modifier = Modifier
                .padding(16.dp)
            ) {
                ToggleButton(navController)
            }
            LazyColumn {
                item {
                    Row(modifier = Modifier
                        .padding(16.dp)
                    ) {
                        Calendar(navController, viewModel)
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        // Add Journal Entry Button
                        Button(
                            onClick = {navController.navigate("newJournal")},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            colors = ButtonDefaults.buttonColors(selectedColor)
                        ) {
                            Text(
                                "Add Journal Entry",
                                style = TextStyle(color = Color.Black, fontSize = 16.sp, textAlign = TextAlign.Center),
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                }

                item {
                    Column(
                        modifier = Modifier.padding(bottom = 60.dp)
                    ) {
                        if (sortedJournals.isEmpty()) {

                            Row(
                                modifier = Modifier.padding(12.dp)
                            ) {
                                JournalEntry(text = "No Journal Entries Found", null)
                            }

                        } else {

                            Row(
                                modifier = Modifier.padding(12.dp)
                            ) {
                                JournalEntry(
                                    text = sortedJournals.elementAt(0).content,
                                    date = sortedJournals.elementAt(0).created_at?.date
                                )
                            }

                            Row(
                                modifier = Modifier.padding(12.dp)
                            ) {
                                JournalEntry(
                                    text = sortedJournals.elementAt(1).content,
                                    date = sortedJournals.elementAt(1).created_at?.date
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ToggleButton(navController: NavController) {
    val currentDestination = navController.currentDestination?.route

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        RoundedEndButton(
            text = "Monthly",
            isSelected = currentDestination == "calendar",
            onClick = { navController.navigate("calendar") },
            isFirstButton = true
        )
        RoundedEndButton(
            text = "Weekly",
            isSelected = currentDestination == "weekly",
            onClick = { navController.navigate("weekly") },
            isFirstButton = false
        )
    }
}

@Composable
fun RoundedEndButton(text: String, isSelected: Boolean, onClick: () -> Unit, isFirstButton: Boolean) {
    val selectedColor = Color(0xFFB98231)
    val unselectedColor = Color(0xFFE8D5BA)

    val cornerRadiusStart = if (isFirstButton) 20.dp else 0.dp
    val cornerRadiusEnd = if (!isFirstButton) 20.dp else 0.dp

    val shape = RoundedCornerShape(
        topStart = if (isFirstButton) cornerRadiusStart else 0.dp,
        topEnd = if (!isFirstButton) cornerRadiusEnd else 0.dp,
        bottomStart = if (isFirstButton) cornerRadiusStart else 0.dp,
        bottomEnd = if (!isFirstButton) cornerRadiusEnd else 0.dp
    )

    Box(
        modifier = Modifier
            .clickable { onClick() }
            .size(width = 150.dp, height = 60.dp)
            .clip(shape)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = shape
            )
            .background(color = if (isSelected) selectedColor else unselectedColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = TextStyle(color = Color.Black, fontSize = 16.sp, textAlign = TextAlign.Center)
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calendar(navController: NavController, viewModel: CalendarViewModel) {
    val selectedColor = Color(0xFFB98231)
    val unselectedColor = Color(0xFFE8D5BA)
    Column(
        modifier = Modifier
            .background(unselectedColor, shape = RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier
                .background(selectedColor, shape = RoundedCornerShape(10.dp))
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            // Change Month Backwards Button
            Button(
                onClick = {
                    viewModel.month.value = viewModel.month.value.minusMonths(1)
                    viewModel.shownDate.value = LocalDate.of(viewModel.month.value.year, viewModel.month.value.month, 1)
                },
                colors = ButtonDefaults.buttonColors(unselectedColor),
                border = BorderStroke(width = 1.dp, color = Color.Black)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "backward",
                    tint = Color.Black,
                )
            }
            Text(
                viewModel.month.value.toString(),
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .width(160.dp),
            )
            // Change Month Forward Button
            Button(
                onClick = {
                    viewModel.month.value = viewModel.month.value.plusMonths(1)
                    viewModel.shownDate.value = LocalDate.of(viewModel.month.value.year, viewModel.month.value.month, 1)
                },
                colors = ButtonDefaults.buttonColors(unselectedColor),
                border = BorderStroke(width = 1.dp, color = Color.Black)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "forward",
                    tint = Color.Black,
                )
            }

        }
        // fill calendar with weekButtons
        val firstDay = LocalDate.of(viewModel.month.value.year, viewModel.month.value.month, 1)
        var counter = firstDay
        var firstDayOfWeek = counter.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))

        while (counter.month == viewModel.month.value.month || firstDayOfWeek.month == viewModel.month.value.month){
            val weekDates = mutableListOf<LocalDate>()
            for (i in 0 until 7) {
                weekDates.add(firstDayOfWeek.plusDays(i.toLong()))
            }
            WeekButtons(navController, weekDates, viewModel)
            counter = counter.plusDays(7)
            firstDayOfWeek = counter.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayButton(navController: NavController, day: LocalDate, showDate: Boolean, isToday: Boolean) {
    val selectedColor = Color(0xFFB98231)
    val unselectedColor = Color(0xFFE8D5BA)
    TextButton(
        onClick = {navController.navigate("daily/$day")},
        shape = CircleShape,
        modifier = Modifier
            .width(48.dp)
            .padding(2.dp),
        colors = ButtonDefaults.buttonColors(if(isToday and showDate)selectedColor else unselectedColor ),
        border = BorderStroke(width = 1.dp, color = Color.Black),
    ) {
        Text(text = if(showDate)day.dayOfMonth.toString() else "",
            color = Color.Black,
            fontWeight = FontWeight.Bold)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeekButtons(navController: NavController, weekDates : List<LocalDate>, viewModel: CalendarViewModel) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        for(date in weekDates) { DayButton(
            navController,
            date,
            (date.month == viewModel.month.value.month),
            date.isEqual(viewModel.today)
            )
        }
    }
}

@Composable
fun JournalEntry (text: String, date: kotlinx.datetime.LocalDate?) {
    val unselectedColor = Color(0xFFE8D5BA)
    if (date == null) {
        Column(
            modifier = Modifier
                .background(unselectedColor, shape = RoundedCornerShape(10.dp))
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Text(text, fontWeight = FontWeight.Bold, fontSize = 24.sp)
        }
    }
    else {
        Column(
            modifier = Modifier
                .background(unselectedColor, shape = RoundedCornerShape(10.dp))
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Text(date.toString(), fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Text(text, fontSize = 20.sp)
        }
    }
}

class LocalDateTimeComparator : Comparator<JournalEntryData> {
    override fun compare(j1: JournalEntryData, j2: JournalEntryData): Int {
        return j1.created_at?.let { j2.created_at?.compareTo(it) } ?: 0
    }
}