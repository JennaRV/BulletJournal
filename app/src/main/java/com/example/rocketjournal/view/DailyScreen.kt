package com.example.rocketjournal.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
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
import com.example.rocketjournal.viewmodel.EventViewModel
import com.example.rocketjournal.viewmodel.JournalEntryViewModel
import com.example.rocketjournal.viewmodel.ListsViewModel
import kotlinx.datetime.toJavaLocalDate
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DailyScreen(
    navController : NavController,
    date : String?,
    JEViewModel: JournalEntryViewModel = hiltViewModel(),
    LViewModel: ListsViewModel = hiltViewModel(),
    EViewModel: EventViewModel = hiltViewModel()
) {
    val primaryColor = Color(0xFF606BD1)
    val secondaryColor = Color(0xFFBA355D)
    val selectedColor = Color(0xFFB98231)
    val unselectedColor = Color(0xFFE8D5BA)
    val day = LocalDate.parse(date)
    val eventsState = EViewModel.eventList.collectAsState(initial = emptyList()).value ?: emptyList()
    val listsState = LViewModel.listFlow.collectAsState(initial = emptyList()).value ?: emptyList()
    val journalsState = JEViewModel.entryList.collectAsState(initial = emptyList()).value ?: emptyList()
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
        Button(
            onClick = {navController.popBackStack()},
            colors = ButtonDefaults.buttonColors(selectedColor),
            border = BorderStroke(width = 1.dp, color = Color.Black)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "backward"
            )
        }

        Row (
            modifier = Modifier.padding(12.dp)
        ){
            Text(
                day.dayOfWeek.toString()
                + ", "
                + day.month
                + " "
                + day.dayOfMonth,
                modifier = Modifier
                    .weight(.35f)
                    .padding(horizontal = 10.dp)
                    .background(unselectedColor, shape = RoundedCornerShape(10.dp))
                    .border(
                        BorderStroke(width = 1.dp, color = Color.Black),
                        shape = RoundedCornerShape(10.dp)
                    ),
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                ),
            )
        }

        LazyColumn(modifier = Modifier.padding(bottom = 60.dp)) {
            // Events
            item {
                Row (
                    modifier = Modifier.padding(12.dp)
                ){
                    Text(
                        "Events",
                        modifier = Modifier
                            .weight(.35f)
                            .padding(horizontal = 10.dp)
                            .background(selectedColor, shape = RoundedCornerShape(10.dp))
                            .border(
                                BorderStroke(width = 1.dp, color = Color.Black),
                                shape = RoundedCornerShape(10.dp)
                            ),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center
                        ),
                    )
                }
            }

            item {
                var eventFound = false
                eventsState.forEach {
                    if (it.date_time.date.toJavaLocalDate() >= LocalDate.parse(date)) {
                        eventFound = true
                        Row(modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)) {
                            EventEntry(it.name, it.details, it.date_time.date.toJavaLocalDate())
                        }
                    }
                }
                if (!eventFound) {
                    Row(modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)) {
                        EventEntry()
                    }
                }
            }

            // List

            item {
                Row (
                    modifier = Modifier.padding(12.dp)
                ){
                    Text(
                        "Lists",
                        modifier = Modifier
                            .weight(.35f)
                            .padding(horizontal = 10.dp)
                            .background(selectedColor, shape = RoundedCornerShape(10.dp))
                            .border(
                                BorderStroke(width = 1.dp, color = Color.Black),
                                shape = RoundedCornerShape(10.dp)
                            ),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center
                        ),
                    )
                }
            }

            item {
                var listFound = false
                listsState.forEach {
                    if (it.deadline?.date?.toJavaLocalDate()?.isAfter(LocalDate.parse(date)) == true || it.deadline == null) {
                        listFound = true
                        Row(modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)) {
                            ListEntry(navController,it.name, it.list_id)
                        }
                    }
                }
                if (!listFound) {
                    Row(modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)) {
                        ListEntry(navController)
                    }
                }
            }


            // Journals
            item {
                Row (
                    modifier = Modifier.padding(12.dp)
                ){
                    Text(
                        "Journals",
                        modifier = Modifier
                            .weight(.35f)
                            .padding(horizontal = 10.dp)
                            .background(selectedColor, shape = RoundedCornerShape(10.dp))
                            .border(
                                BorderStroke(width = 1.dp, color = Color.Black),
                                shape = RoundedCornerShape(10.dp)
                            ),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center
                        ),
                    )
                }
            }
            item {
                var journalFound = false
                journalsState.forEach {
                    if (it.created_at!!.date.toJavaLocalDate() == LocalDate.parse(date)) {
                        journalFound = true
                        Row(modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)) {
                            JournalEntry(it.content)
                        }
                    }
                }
                if (!journalFound) {
                    Row(modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)) {
                        JournalEntry()
                    }
                }
            }
        }
    }
}

@Composable
fun EventEntry(name: String = "No events found for this date", text: String? = null, date: LocalDate = LocalDate.now()) {
    val unselectedColor = Color(0xFFE8D5BA)
    Row(
        modifier = Modifier
            .background(unselectedColor, shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 24.dp, vertical = 12.dp)
            .fillMaxWidth()
    ) {
        Column {
            if (text == null) {
                Text(
                    name,
                    modifier = Modifier.padding(horizontal = 5.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            else {
                Text(
                    name + ": " + date.month.toString() + " " + date.dayOfMonth.toString() + ", " + date.year.toString(),
                    modifier = Modifier.padding(horizontal = 5.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text,
                    modifier = Modifier.padding(horizontal = 5.dp),
                    fontSize = 20.sp,
                )
            }
        }
    }
}

@Composable
fun ListEntry(navController: NavController, text: String = "No lists found for this date", listID: Int? = null) {
    val unselectedColor = Color(0xFFE8D5BA)
    Button(
        onClick = {navController.navigate("task_list/$listID")},
        modifier = Modifier
            .background(unselectedColor, shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 24.dp, vertical = 12.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
    ) {
        Text(
            text,
            modifier = Modifier.padding(horizontal = 5.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun JournalEntry(text: String = "No journal entries found for this date") {
    val unselectedColor = Color(0xFFE8D5BA)
    Row(
        modifier = Modifier
            .background(unselectedColor, shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 24.dp, vertical = 12.dp)
            .fillMaxWidth()
    ) {
        Text(
            text,
            modifier = Modifier.padding(horizontal = 5.dp),
            fontSize = 20.sp,
        )
    }
}

