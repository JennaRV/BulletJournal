package com.example.test

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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



import com.example.rocketjournal.view.BottomNavigationBar
import com.example.rocketjournal.viewmodel.CalendarViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeeklyScreen(navController: NavController, viewModel: CalendarViewModel = hiltViewModel()) {
    val primaryColor = Color(0xFF606BD1)
    val secondaryColor = Color(0xFFBA355D)
    Column(modifier = Modifier
        .background(primaryColor)
        .drawBehind {
            drawCircle(
                color = secondaryColor,
                radius = 700f,
                center = Offset(size.width / 2, size.height / 6)
            )
        }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            ToggleButton(navController)
        }

        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            ThreePartWidget(viewModel)
        }
        for(i in 0..6) {
            DayEntry(viewModel.currentWeekStart.value.plusDays(i.toLong()))
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ThreePartWidget(viewModel: CalendarViewModel) {
    val backgroundColor = Color(0xFFB98231)
    val mainColor = Color(0xFFE8D5BA)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { viewModel.currentWeekStart.value = viewModel.currentWeekStart.value.minusDays(7) },
            modifier = Modifier.fillMaxHeight(),
            colors = ButtonDefaults.buttonColors(backgroundColor),
            border = BorderStroke(width = 1.dp, color = Color.Black)
        ) {
            Text(
                "<---" // replace with icon
            )
        }
        Text(
            viewModel.currentWeekStart.value.month.toString()
                    + "\n"
                    + viewModel.currentWeekStart.value.dayOfMonth
                    + "-"
                    + viewModel.currentWeekStart.value.plusDays(6).dayOfMonth,
            modifier = Modifier
                .weight(.35f)
                .padding(horizontal = 10.dp)
                .background(mainColor, shape = RoundedCornerShape(10.dp))
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
        Button(
            onClick = { viewModel.currentWeekStart.value = viewModel.currentWeekStart.value.plusDays(7) },
            modifier = Modifier.fillMaxHeight(),
            colors = ButtonDefaults.buttonColors(backgroundColor),
            border = BorderStroke(width = 1.dp, color = Color.Black)
        ) {
        Text(
                "--->" // replace with icon
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayEntry(date: LocalDate) {
    val backgroundColor = Color(0xFFB98231)
    val mainColor = Color(0xFFE8D5BA)
    Row(
        modifier = Modifier
            .padding(16.dp)
            .background(mainColor, shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .border(
                BorderStroke(width = 1.dp, color = Color.Black),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Text(
            date.format(DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH))
                    + " "
                    + date.dayOfMonth,
            modifier = Modifier
                .background(backgroundColor, shape = RoundedCornerShape(10.dp))
                .padding(4.dp)
                .height(48.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        ) // Display the day

        Text(
            "Placeholder Journal Entry\n Lorem Ipsum",
            modifier = Modifier
                .padding(4.dp)
        ) // Display the text
    }
}

