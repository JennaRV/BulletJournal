package com.example.rocketjournal.view

//import android.app.TimePickerDialog
import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Build
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.TimePicker
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rocketjournal.model.Repositories.RepoImplementation.ListsRepositoryImpl
import com.example.rocketjournal.viewmodel.ListsViewModel
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import javax.inject.Scope


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateList(viewModel: ListsViewModel = hiltViewModel()) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val desiredHeight = screenHeight * 0.85f

    //Date and Time variables
    val date = remember {
        Calendar.getInstance().apply {
            set(Calendar.YEAR, 2023)
            set(Calendar.MONTH, 7)
            set(Calendar.DAY_OF_MONTH, 23)
        }.timeInMillis
    }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = date
    )
    var showDatePicker by remember { mutableStateOf(false) }

    val timePickerState = rememberTimePickerState(
        initialHour = 12,
        initialMinute = 30,
    )
    var showTimePicker by remember { mutableStateOf(false) }

    val name by viewModel.name.collectAsState()
    //val deadline by viewModel.deadline.collectAsState()

    //mutable variables

    var isDatePickerVisible by remember { mutableStateOf(false) }
    var isTimePickerVisible by remember { mutableStateOf(false) }
//    val selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var selectedTime by remember { mutableStateOf<LocalTime?>(null) }
    var listName by remember { mutableStateOf("") } // Store the list name here
    var selectedDateTime: LocalDateTime? by remember { mutableStateOf(null) } // Store the selected date and time here

    OpenSheetButton(onClick = {
        scope.launch {
            sheetState.show()
        }
    })

    if (sheetState.isVisible) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                scope.launch {
                    sheetState.hide()
                }
            },
            modifier = Modifier

        ) {
            var listDeadlineDate: LocalDate
            // Adjust this container to control the height of your bottom sheet
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(desiredHeight)
                    .padding(horizontal = 40.dp),
            ) {
                Column(

                    modifier = Modifier

                        .fillMaxWidth()
                        //Use a fraction of the screen height
                        .height(desiredHeight)
                        .padding(horizontal = 40.dp),

                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text("New List", modifier = Modifier.padding(16.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = name,
                        onValueChange = { viewModel.name.value = it },
                        label = { Text("List Name") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        shape = RoundedCornerShape(60.dp)
                    )

                    Box(modifier = Modifier) {
                        DateTimePickerComponent(viewModel)
                    }
                    PlaceholderLazyColumn()
                    }
                }
            //AddListItemText {}
            Button(
                onClick = {
                    //Log.e("", "$deadline")
                    // Call the view model function to add the list
                        viewModel.onCreateList()
                        viewModel.name.value = ""
                        scope.launch {
                            sheetState.hide()
                        }

                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(20.dp)
            ) {
                Text("Add List")
            }
        }
    }
}

@Composable
fun AddListItemText(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp) // Adjust vertical padding as needed
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(top = 8.dp) // Adjust top padding to move it to the top
                .fillMaxWidth(),

            verticalAlignment = Alignment.Top, // Align to the top
            horizontalArrangement = Arrangement.Center // Center horizontally
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add"

            )
            Text(
                text = "Add List Item",
                modifier = Modifier.padding(start = 8.dp) // Add padding between icon and text
            )
        }
    }
}


@Composable
fun PlaceholderLazyColumn() {
    val items = List(2) { index -> index } // Creating a list of size 2 with dummy values
    LazyColumn {
        itemsIndexed(items) { index, _ ->
            Box(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .height(200.dp)
                    .background(Color.Black)
                    .padding(8.dp)
            )
        }
    }
}


@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePickerComponent(viewModel: ListsViewModel) {
    val datePickerState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var selectedTime by remember { mutableStateOf<LocalTime?>(null) } // Declare selectedTime
    val selectedDateMillis = datePickerState.selectedDateMillis
    var selectedDateText by remember { mutableStateOf("No Date Selected") }
    var selectedTimeText by remember { mutableStateOf("No Date Selected") }

    val timePickerState = rememberTimePickerState()
    var showTimePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
       // verticalArrangement = Arrangement.Center,
    ) {

        Text(text = selectedDateText, modifier = Modifier.padding(bottom = 16.dp))
        //Date Picker
        Button(
            onClick = {
                showDatePicker = true //changing the visibility state
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Date Picker")
        }

        Divider(modifier = Modifier.padding(vertical = 24.dp))

        Text(text = selectedTimeText, modifier = Modifier.padding(bottom = 16.dp))

        //time picker
        Button(
            onClick = {
                showTimePicker = true //changing the visibility state
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Time Picker")
        }

    }

    // date picker component
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        val selectedDateMillis = datePickerState.selectedDateMillis
                        if (selectedDateMillis != null) {
                            // Convert milliseconds to LocalDateTime
                            val selectedDate = Instant.fromEpochMilliseconds(selectedDateMillis)
                                .toLocalDateTime(TimeZone.currentSystemDefault())
                            // Format the date
                            val formattedDate = "${selectedDate.monthNumber}/${selectedDate.dayOfMonth}/${selectedDate.year}"
                            selectedDateText = formattedDate
                        } else {
                            // Handle error or display a message
                        }
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
                        val selectedHour = if(timePickerState.hour > 12) timePickerState.hour-12 else timePickerState.hour
                        val selectedMinute = timePickerState.minute
                        if (selectedHour != null && selectedMinute != null) {
                           // val selectedTime = selectedTime
                            // Store or process the selected time as needed
                            // For example, you can format it and display it in the UI
                            selectedTimeText = "$selectedHour:$selectedMinute"
                        } else {
                            // Handle error or display a message
                        }
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

    //combine date and time
    val combinedDateTime: LocalDateTime? = selectedDate?.let {
        selectedTime?.let { time ->
            LocalDateTime(selectedDate!!.year, selectedDate!!.month, selectedDate!!.dayOfMonth, time.hour, time.minute)
        }
    }
    viewModel.setDeadline(combinedDateTime)

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
                    color = containerColor
                ),
            color = containerColor
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
                    Spacer(modifier = Modifier.weight(1f))
                    dismissButton?.invoke()
                    confirmButton()
                }
            }
        }
    }
}

@Composable
fun OpenSheetButton(onClick: () -> Unit) {
    Button(onClick = onClick,
        modifier = Modifier
        .padding(horizontal = 16.dp, vertical = 8.dp) // Adjust padding as needed
    .border(
        BorderStroke(2.dp, Color.Black),
        shape = RoundedCornerShape(15.dp)
    )
        .height(50.dp),
    shape = RoundedCornerShape(60.dp),
    colors = ButtonDefaults.buttonColors(
        containerColor = Color(0xFFB98231)
    )
        ) {
        Text("Add List",
            style = TextStyle(color = Color.Black)
        )
    }
}