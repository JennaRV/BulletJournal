package com.example.test

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeeklyScreen() {
    val primaryColor = Color(0xFF606BD1)
    val secondaryColor = Color(0xFFBA355D)
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
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            toggleButton()
        }

        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            ThreePartWidget()
        }

        DayEntry("Sun", 18) // make more concise
        DayEntry("Mon", 19)
        DayEntry("Tue", 20)
        DayEntry("Wed", 21)
        DayEntry("Thur", 22)
        DayEntry("Fri", 23)
        DayEntry("Sat", 24)
    }
}

@Preview
@Composable
fun ThreePartWidget() {
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
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxHeight(),
            colors = ButtonDefaults.buttonColors(backgroundColor),
            border = BorderStroke(width = 1.dp, color = Color.Black)
        ) {
            Text(
                "<---" // replace with icon
            )
        }
        Text(
            "February\n 18-24",
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
            onClick = { /*TODO*/ },
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

@Composable
fun DayEntry(day: String, date: Int) {
    val backgroundColor = Color(0xFFB98231)
    val mainColor = Color(0xFFE8D5BA)
    Row(
        modifier = Modifier
            .padding(16.dp)
            .background(mainColor, shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .border(BorderStroke(width = 1.dp, color = Color.Black),  shape = RoundedCornerShape(10.dp))
    ) {
        Text(
            "$day $date",
            modifier = Modifier
                .background(backgroundColor, shape = RoundedCornerShape(10.dp))
                .padding(4.dp)
                .height(48.dp), //band-aid solution, does not responsively scale ,
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

@Preview
@Composable
fun DayEntryPreview() {
    DayEntry(day = "Sun", date = 25)
}

