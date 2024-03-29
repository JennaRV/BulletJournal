package com.example.rocketjournal.view.Journal

import AppBackgroundGeneral
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rocketjournal.view.BackButton
import java.time.LocalDate


//import com.example.rocketjournal.view.Journal.


//@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewJournalScreen(navController: NavController) {
    AppBackgroundGeneral {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp

        val boxWidth = screenWidth * 0.9f
        val offsetX = screenWidth * 0.05f

        Column {

            //A back button
            Row {
                BackButton(navController = navController,"journal")  //Add navController = navController Later
                Spacer(modifier = Modifier.weight(1f))
                SettingsButton(onClick = { /* Handle settings button click */ })


            }

            Spacer(modifier = Modifier.size(20.dp))


            Button(
                onClick = { /* Handle button click */ },
                modifier = Modifier
                    .offset(offsetX, screenHeight * 0.0012f)
                    .size(boxWidth, 60.dp)
                    .clipToBounds()
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(15.dp))
                    .shadow(10.dp, RoundedCornerShape(15.dp)),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB98231)
                )

            ) {
                Text(
                    text = "Details",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }


            Spacer(modifier = Modifier.size(20.dp))

            var title by remember { mutableStateOf(TextFieldValue()) }
            var content by remember { mutableStateOf(TextFieldValue()) }

            //Small Button for "New Entries"Column(
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                daterPicker()

                val state = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
                DatePicker(state = state,
                    modifier = Modifier.padding(16.dp)
                )

                TextField(
                    value = content,
                    onValueChange = { content = it },
                    label = { Text("Content") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f), // Expand as the user continues writing
                    maxLines = Int.MAX_VALUE,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                        //maxLines = Int.MAX_VALUE
                    ),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = MaterialTheme.typography.bodySmall.fontSize
                    ),
                    shape = RoundedCornerShape(15.dp)
                )

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .offset(offsetX * 0.0012f, screenHeight * 0.0012f)
                        .size(boxWidth, 60.dp)
                        .border(1.dp, Color.Black, shape = RoundedCornerShape(15.dp))
                        .shadow(10.dp, RoundedCornerShape(15.dp))
                        .align(CenterHorizontally),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFB98231)
                    )) {
                    Text(
                        text = "Save",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }




            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun daterPicker() {
    //TODO
    val state = rememberDatePickerState()
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        DatePickerDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("CANCEL")
                }
            },
            modifier = Modifier
                .padding(16.dp)
                //.background(Color(0xFFE8D5BA)) // Set background color
                //.border(1.dp, Color.Black, shape = RoundedCornerShape(15.dp))//
                .animateContentSize()
                .shadow(10.dp)
                .background(shape = RoundedCornerShape(15.dp), color = Color(0xFFE8D5BA)),
            colors = DatePickerDefaults.colors(
                containerColor = Color.White,
                titleContentColor = Color.Yellow,
                headlineContentColor = Color.Green,
                weekdayContentColor = Color.Blue,
                selectedDayContentColor = Color(red = 214, green = 66, blue = 105),
            ),
            shape = RoundedCornerShape(15.dp),
        ) {
            DatePicker(
                state = state,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE8D5BA)) // Set background color
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                showModeToggle = false

            )
        }
    }


//    DatePicker(
//        state = state,
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color(0xFFE8D5BA)) // Set background color
//            .padding(horizontal = 16.dp, vertical = 8.dp)
//            .border(1.dp, Color.Black, shape = RoundedCornerShape(15.dp)),
//
//    )


}
@Preview
@Composable
fun DatePickerTest(){
    val date = remember { LocalDate.now() }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = date.toString())
    }
}
