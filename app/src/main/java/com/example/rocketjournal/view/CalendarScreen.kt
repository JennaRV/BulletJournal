package com.example.test

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun CalendarScreen() {
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
        Column (modifier = Modifier
            .fillMaxSize()) {
            Row(modifier = Modifier
                .padding(16.dp)
            ) {
                toggleButton()
            }

            Row(modifier = Modifier
                .padding(16.dp)
            ) {
                Calendar()
            }

            Row(
                modifier = Modifier
                    .background(primaryColor)
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(secondaryColor)
                ) {
                    Text(
                        "Add Journal Entry",
                        style = TextStyle(color = Color.Black, fontSize = 16.sp, textAlign = TextAlign.Center),
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            Row(
                modifier = Modifier.padding(12.dp)
            ) {
                journalEntry(text = "Journal Entry #1")
            }

            Row(
                modifier = Modifier.padding(12.dp)
            ) {
                journalEntry(text = "Journal Entry #2")
            }
        }
    }
}

@Preview
@Composable
fun toggleButton() {
    val selectedColor = Color(0xFFB98231)
    val unselectedColor = Color(0xFFE8D5BA)
    var leftSelected = remember {mutableStateOf(true)}

    Row{
        Button(
            onClick = { leftSelected.value = !leftSelected.value },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(unselectedColor),
            border = BorderStroke(width = 2.dp, color = Color.Black)
        ) {
            Row(
                modifier = Modifier
                    .padding(0.dp)
                    .weight(1f)
                    .border(BorderStroke(width = 1.dp, color = Color.Black))
            ) {
                Text(
                    "Monthly",
                    modifier = Modifier
                        .background(if (leftSelected.value) selectedColor else unselectedColor)
                        .padding(10.dp)
                        .weight(0.5f),
                    style = TextStyle(color = Color.Black, fontSize = 16.sp, textAlign = TextAlign.Center)
                )
                Text(
                    "Weekly",
                    modifier = Modifier
                        .background(if (!leftSelected.value) selectedColor else unselectedColor)
                        .padding(10.dp)
                        .weight(0.5f),
                    style = TextStyle(color = Color.Black, fontSize = 16.sp, textAlign = TextAlign.Center)
                )
            }
        }
    }
}

@Preview
@Composable
fun Calendar() {
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
                .fillMaxWidth()
        ) {
            Text(
                "Calendar",
                modifier = Modifier
                    .fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
        repeat(4) {weekButtons()}
    }
}

@Composable
fun dayButton() {
    val unselectedColor = Color(0xFFE8D5BA)
    ElevatedButton(
        onClick = { /*TODO*/ },
        shape = CircleShape,
        modifier = Modifier
            .width(48.dp)
            .padding(2.dp),
        colors = ButtonDefaults.buttonColors(unselectedColor),
        border = BorderStroke(width = 1.dp, color = Color.Black)
    ) {

    }
}

@Composable
fun weekButtons() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        repeat(7) {dayButton()}
    }
}

@Composable
fun journalEntry(text: String) {
    val unselectedColor = Color(0xFFE8D5BA)
    Row(
        modifier = Modifier
            .background(unselectedColor, shape = RoundedCornerShape(10.dp))
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Text(text)
    }
}