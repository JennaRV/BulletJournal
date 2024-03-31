package com.example.test

import AppBackgroundGeneral
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.ExposedDropdownMenuDefaults.outlinedTextFieldColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
//import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rocketjournal.model.dataModel.EventData
import com.example.rocketjournal.view.BottomNavigationBar
import com.example.rocketjournal.view.HeaderRow
import com.example.rocketjournal.viewmodel.EventViewModel
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventCreation(navController: NavController) {
    AppBackgroundGeneral {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp

        val boxWidth = screenWidth * 0.9f
        val offsetX = screenWidth * 0.05f

        val sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        )
        val scope = rememberCoroutineScope()

            //Date and Time variables
    val date = remember {
        java.util.Calendar.getInstance().apply {
            set(java.util.Calendar.YEAR, 2023)
            set(java.util.Calendar.MONTH, 7)
            set(java.util.Calendar.DAY_OF_MONTH, 23)
        }.timeInMillis
    }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = date,
        yearRange = 1990..2023
    )
    var showDatePicker by remember { mutableStateOf(false) }

    val timePickerState = rememberTimePickerState(
        initialHour = 12,
        initialMinute = 30,
    )
    var showTimePicker by remember { mutableStateOf(false) }

    //mutable variables
    var isDatePickerVisible by remember { mutableStateOf(false) }
    var isTimePickerVisible by remember { mutableStateOf(false) }
    val selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var selectedTime by remember { mutableStateOf<LocalTime?>(null) }


        Column(modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                drawRect(
                    color = Color.White.copy(alpha = 0.5f),
                    topLeft = Offset(0f, size.height / 30),
                )
            }
        ) {
            Spacer(modifier = Modifier.size(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))
                // Title text
                TitleText(title = "Details")

                // Cancel button aligned to the right
                CancelButton(navController)
            }
            NameTextField()
            Spacer(modifier = Modifier.size(10.dp))
            DetailsTextField()
            Spacer(modifier = Modifier.size(20.dp))
            Box(modifier = Modifier) {
                DateTimePickerComponent()
            }
        }
    }
}



@Composable
fun TitleText(title: String) {
    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .background(
                color = Color.White.copy(alpha = 0.0f),
                shape = RoundedCornerShape(15.dp),
            )
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Details",
            fontSize = 24.sp,
            fontWeight = FontWeight.W800,
            modifier = Modifier.align(Alignment.Center),
            color = Color.White
        )
    }
}
@Composable
fun CancelButton(navController: NavController) {
    Button(
        onClick = { navController.navigate("event") },
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .height(50.dp),
            //.align(Alignment.End),
        shape = RoundedCornerShape(60.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White.copy(alpha = 0.0f)
        )

    ) {
        Text(
            text = "Cancel",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun NameTextField() {
    var eventName = ""
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        value = eventName,
        onValueChange = { eventName = it },
        label = {
            Text(
                "Name",
                color = Color.White
            )
                },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        shape = RoundedCornerShape(10.dp),
    )
}

@Composable
fun DetailsTextField() {
    var eventDetail = ""
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        value = eventDetail,
        onValueChange = { eventDetail = it },
        label = {
            Text(
                "Details",
                color = Color.White
            )
        },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        shape = RoundedCornerShape(10.dp),
    )
}

//@Composable
//fun DateButton() {
//    Button(
//        onClick = { },
//        shape = RoundedCornerShape(10.dp),
//        colors = ButtonDefaults.buttonColors(Color.White.copy(alpha = 0.5f)),
//
//        modifier = Modifier
//            .background(Color.White.copy(alpha = 0.0f), shape = RoundedCornerShape(10.dp))
//            .padding(16.dp)
//            .fillMaxWidth()
//
//    ) {
//        Text(
//            "Date",
//            modifier = Modifier
//                .fillMaxWidth(),
//            fontWeight = FontWeight.Bold,
//            style = TextStyle(
//                color = Color.White,
//                fontSize = 25.sp,
//                textAlign = TextAlign.Center
//            )
//        )
//    }
//}

//@Composable
//fun TimeButton() {
//    Button(
//        onClick = { },
//        shape = RoundedCornerShape(10.dp),
//        colors = ButtonDefaults.buttonColors(Color.White.copy(alpha = 0.5f)),
//
//        modifier = Modifier
//            .background(Color.White.copy(alpha = 0.0f), shape = RoundedCornerShape(10.dp))
//            .padding(16.dp)
//            .fillMaxWidth()
//
//    ) {
//        Text(
//            "Time",
//            modifier = Modifier
//                .fillMaxWidth(),
//            fontWeight = FontWeight.Bold,
//            style = TextStyle(
//                color = Color.White,
//                fontSize = 25.sp,
//                textAlign = TextAlign.Center
//            )
//        )
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePickerComponent() {
    val datePickerState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }


    val timePickerState = rememberTimePickerState()
    var showTimePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        //Date Picker
        Button(
            onClick = {
                showDatePicker = true //changing the visibility state
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Color.White.copy(alpha = 0.5f)),

            modifier = Modifier
                .background(Color.White.copy(alpha = 0.0f), shape = RoundedCornerShape(10.dp))
                .padding(0.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Date",
                style = TextStyle(
                            color = Color.White,
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center
                ),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.size(20.dp))

        //time picker
        Button(
            onClick = {
                showTimePicker = true //changing the visibility state
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Color.White.copy(alpha = 0.5f)),

            modifier = Modifier
                .background(Color.White.copy(alpha = 0.0f), shape = RoundedCornerShape(10.dp))
                .padding(0.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Time",
                style = TextStyle(
                            color = Color.White,
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center
                ),
                fontWeight = FontWeight.Bold
            )
        }

        AddEntryButton()

    }

    // date picker component
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) { Text("OK") }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) { Text("Cancel") }
            }
        )
        {
            DatePicker(state = datePickerState)
        }
    }

// time picker component
    if (showTimePicker) {
        TimePickerDialog(
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                TextButton(
                    onClick = {
                        showTimePicker = false
                    }
                ) { Text("OK") }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showTimePicker = false
                    }
                ) { Text("Cancel") }
            }
        )
        {
            TimePicker(state = timePickerState)
        }
    }



}

@Composable
fun TimePickerDialog(
    title: String = "Select Time",
    onDismissRequest: () -> Unit,
    confirmButton: @Composable (() -> Unit),
    dismissButton: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = AlertDialogDefaults.containerColor
                ),
            color = AlertDialogDefaults.containerColor
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    dismissButton?.invoke()
                    confirmButton()
                }
            }
        }
    }
}

@Composable
fun AddEntryButton() {
    Button(
        onClick = { },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(Color.White.copy(alpha = 0.5f)),

        modifier = Modifier
            .background(Color.White.copy(alpha = 0.0f), shape = RoundedCornerShape(10.dp))
            .padding(16.dp)
            .fillMaxWidth(0.5f)

    ) {
        Text(
            "Add Entry",
            modifier = Modifier
                .fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                color = Color.White,
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
        )
    }
}

