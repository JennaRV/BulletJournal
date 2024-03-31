package com.example.rocketjournal.view

import AppBackgroundGeneral
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rocketjournal.model.DataTransferObjects.ListsDTO
import com.example.rocketjournal.model.dataModel.TaskData
import com.example.rocketjournal.viewmodel.ListsViewModel
import com.example.rocketjournal.viewmodel.TaskViewModel
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@Composable
fun TaskList(
    navController: NavController,
    listID: Int,
    taskViewModel: TaskViewModel = hiltViewModel(),
    listViewModel: ListsViewModel = hiltViewModel())
    {

        val coroutine = rememberCoroutineScope()
        var list by remember { mutableStateOf<ListsDTO?>(null) }


        LaunchedEffect(Unit) {
            coroutine.launch {
                list = listViewModel.getList(listID)
                taskViewModel.getTasks(listID)

            }
        }

        //val list: ListsDTO? = listViewModel.getList(listID)

    val taskFlow = taskViewModel.taskList.collectAsState(initial = emptyList()).value ?: emptyList()

    val isLoading = taskViewModel.isLoading.collectAsState(initial = true).value

        AppBackgroundGeneral {

            Column {

                BackButton2(navController)
                list?.let { TaskListHeader(it.name) }

                LazyColumn {
                    items(
                        items = taskFlow,
                        key = { task -> task.task_id ?: -1 } // Use a unique identifier for each task, like its ID
                    ) { task ->
                        TaskItemView(
                            task = task,
                            onTaskClicked = { clickedTask ->
                                // Handle task click here
                            },
                            onDeleteClicked = {
                                  taskViewModel.deleteTask(task)
                            },
                            taskViewModel = taskViewModel
                        )
                    }
                }
                AddListItemButton(listID = listID)

            }


        }

}







    @Composable
    fun TaskListHeader(listName: String, listViewModel: ListsViewModel = hiltViewModel()) {

        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp) // Adjust padding as needed
                .border(
                    BorderStroke(2.dp, Color.Black),
                    shape = RoundedCornerShape(15.dp)
                ) // Border with rounded corners
                .background(
                    color = Color(0xFFB98231),
                    shape = RoundedCornerShape(15.dp),
                ) // Background color inside the border
                .fillMaxWidth()
                .padding(16.dp), // Padding inside the border for the content
            contentAlignment = Alignment.Center
        ) {


            Text(
                text = listName,
                fontSize = 24.sp,
                fontWeight = FontWeight.W800,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

@Composable
fun BackButton2(navController: NavController) {
    Button(
        onClick = { navController.navigate("list") },
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
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .size(width = 70.dp, height = 30.dp), // or any size you prefer
            Color.Black
        )
    }
}

@Composable
fun TaskItemView(
    task: TaskData,
    onTaskClicked: (TaskData) -> Unit,
    onDeleteClicked: (TaskData) -> Unit, // Callback for delete action
    taskViewModel: TaskViewModel = hiltViewModel()
) {
    // Remember the checked state of the task
    var isChecked by remember { mutableStateOf(task.is_complete) }

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(BorderStroke(2.dp, Color.Black), RoundedCornerShape(15.dp))
            .background(color = Color(0xFFE8D5BA), shape = RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp)
            .clickable {
                // Toggle the checked state when clicked
                isChecked = !isChecked
                // Update the task's is_complete property and notify the listener
                val updatedTask = task.copy(is_complete = isChecked)
                onTaskClicked(updatedTask)
            },
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isChecked, // Use the stored checked state
                onCheckedChange = { isCheckedNew ->
                    // Update the stored checked state
                    isChecked = isCheckedNew
                    // Update the task's is_complete property and notify the listener
                    val updatedTask = task.copy(is_complete = isCheckedNew)
                    taskViewModel.updateTask(updatedTask)
                    onTaskClicked(updatedTask)
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFFB98231), // Color when checked
                    uncheckedColor = Color.Black, // Color when unchecked
                    checkmarkColor = Color(0xFFE8D5BA) // Color of the checkmark
                ),
                modifier = Modifier,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(task.name)
            // Delete icon
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = Color.Black, // Adjust the color as needed
                modifier = Modifier
                    .clickable {
                        onDeleteClicked(task) // Invoke the delete callback
                    }
            )
        }
    }
}

@Composable
fun AddListItemButton(listID: Int, taskViewModel: TaskViewModel = hiltViewModel()) {
    val name by taskViewModel.name.collectAsState()
    val showDialog = remember { mutableStateOf(false) }
    val taskViewModel: TaskViewModel = hiltViewModel()

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showDialog.value = false
                // Handle dismiss if needed
            },
            title = {
                Text("Add new Item")
            },
            text = {
                TextField(
                    value = name,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onValueChange = { taskViewModel.name.value = it }, // Use a state variable to manage the text input
                    label = { Text("new item") }
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog.value = false
                        taskViewModel.onCreateTask(listID)
                        taskViewModel.name.value = ""
                        // Clear the text input
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog.value = false
                        taskViewModel.name.value = ""
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }

    Button(
        onClick = { showDialog.value = true },
        modifier = Modifier.padding(16.dp), // Adjust padding as needed
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFFB98231))
    ) {
        Text(
            text = "Add List Item",
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}