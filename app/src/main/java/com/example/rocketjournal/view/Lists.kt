package com.example.rocketjournal.view

import AppBackgroundGeneral
import android.os.Build
import android.widget.ListView
import androidx.annotation.RequiresApi

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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rocketjournal.model.dataModel.ListData // Ensure you have a data model for your lists
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
//import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rocketjournal.model.DataTransferObjects.ListsDTO
import com.example.rocketjournal.model.dataModel.TaskData
import com.example.rocketjournal.viewmodel.ListsViewModel
import com.example.rocketjournal.viewmodel.TaskViewModel
import kotlinx.coroutines.launch

//import io.github.jan.supabase.postgrest.Postgrest.Companion.key

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListsScreen(navController: NavController, viewModel: ListsViewModel = hiltViewModel(), taskViewModel: TaskViewModel = hiltViewModel()) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val boxWidth = screenWidth * 1f
    val offsetX = screenWidth * 0.05f
    //the listsState is a list of all to-do lists, this will fetch the lists from the database,
    //its initial/default value is an empty list, if the database is empty, otherwise it will be populates with
    //the to-do lists
    val listsState = viewModel.listFlow.collectAsState(initial = emptyList()).value ?: emptyList()
    //this is the boolean that states that the program is in a "loading state"
    val isLoading = viewModel.isLoading.collectAsState(initial = true).value

    val coroutine = rememberCoroutineScope()
    val tasksMap by remember { mutableStateOf(mutableMapOf<Int, List<TaskData>>()) }




    AppBackgroundGeneral {
        Column {
            BackButton(navController,"home")
            MyListsHeader()
            CreateList()

            //this is where the lists are displayed, it will either have a loading progress indicator,
            //display that o lists were fetched, or display the lists
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally).testTag("progress"))
            } else if (listsState.isEmpty()) {
                Text("No lists available", modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                LazyColumn (modifier = Modifier .size(boxWidth, 450.dp).testTag("listColumn")){
                    items(listsState) { list ->
                        ListDataItemView(
                            list = list,
                            onListClicked = { clickedList ->
                                // Handle navigation or any other action here
                                // For example, navigate to another screen
                                navController.navigate("task_list/${clickedList.list_id}")
                            },
                            onDeleteClicked = {
                                viewModel.deleteList(list)
                            }
                        )
                    }
                }

            }

        }

    }
}

@Composable
fun MyListsHeader() {
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
            text = "My Lists",
            fontSize = 24.sp,
            fontWeight = FontWeight.W800,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun BackButton(navController: NavController, route: String) {
    Button(
        onClick = { navController.navigate("$route") },
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp) // Adjust padding as needed
            .border(
                BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(25.dp)
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
fun ListDataItemView(
    list: ListData,
    onListClicked: (ListData) -> Unit,
    onDeleteClicked: (ListData) -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(BorderStroke(2.dp, Color.Black), RoundedCornerShape(15.dp))
            .background(color = Color(0xFFE8D5BA), shape = RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp)
            .clickable { onListClicked(list) }, // Use clickable modifier
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = list.is_complete,
                onCheckedChange = { isChecked ->
                    onListClicked(list.copy(is_complete = isChecked))
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFFB98231), // Color when checked
                    uncheckedColor = Color.Black, // Color when unchecked
                    checkmarkColor = Color(0xFFE8D5BA) // Color of the checkmark
                ),
                modifier = Modifier,

                )
            Spacer(modifier = Modifier.width(8.dp))
            Text(list.name)
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = Color.Black, // Adjust the color as needed
                modifier = Modifier
                    .clickable {
                        onDeleteClicked(list) // Invoke the delete callback
                    }
            )
        }
    }
}


//@Composable
//fun ListDataItemView2(
//    list: ListData,
//    viewModel: ListsViewModel,
//    navController: NavController
//) {
//    val isChecked = list.list_id in viewModel.checkedLists.value
//
//    Row(
//        modifier = Modifier
//            .padding(horizontal = 16.dp, vertical = 8.dp)
//            .border(BorderStroke(2.dp, Color.Black), RoundedCornerShape(15.dp))
//            .background(color = Color(0xFFE8D5BA), shape = RoundedCornerShape(15.dp))
//            .fillMaxWidth()
//            .height(100.dp)
//            .padding(16.dp)
//            .clickable {
//                viewModel.toggleList(list.list_id) // Toggle the checked state in the ViewModel
//            },
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Checkbox(
//            checked = isChecked,
//            onCheckedChange = { isChecked ->
//                viewModel.toggleList(list.list_id) // Toggle the checked state in the ViewModel
//            },
//            colors = CheckboxDefaults.colors(
//                checkedColor = Color(0xFFB98231), // Color when checked
//                uncheckedColor = Color.Black, // Color when unchecked
//                checkmarkColor = Color(0xFFE8D5BA) // Color of the checkmark
//            )
//        )
//        Spacer(modifier = Modifier.width(8.dp))
//        Text(list.name)
//    }
//}


