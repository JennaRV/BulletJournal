package com.example.rocketjournal.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.rocketjournal.viewmodel.CalendarViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DailyScreen(navController : NavController, date : String?) { //viewModel: CalendarViewModel = hiltViewModel()
    val primaryColor = Color(0xFF606BD1)
    val secondaryColor = Color(0xFFBA355D)
    val selectedColor = Color(0xFFB98231)
    val unselectedColor = Color(0xFFE8D5BA)
    val day = LocalDate.parse(date)
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
            Text(
                "<---" // replace with icon
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
        // Events
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
        Row(modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)) {
            ItemEntry()
        }
        Row(modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)) {
            ItemEntry()
        }

        // List
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
        Row(modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)) {
            ItemEntry()
        }
        Row(modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)) {
            ItemEntry()
        }

        // Journals

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
        Row(modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)) {
            ItemEntry()
        }
        Row(modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)) {
            ItemEntry()
        }


    }
    BottomNavigationBar(navController)
}

@Composable
fun ItemEntry(text: String = "Placeholder") {
    val unselectedColor = Color(0xFFE8D5BA)
    Row(
        modifier = Modifier
            .background(unselectedColor, shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 24.dp, vertical = 12.dp)
            .fillMaxWidth()
    ) {
        Text(
            text,
            modifier = Modifier.padding(horizontal = 5.dp))
    }
}
