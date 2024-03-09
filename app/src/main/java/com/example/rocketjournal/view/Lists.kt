package com.example.rocketjournal.view

import AppBackgroundGeneral
import android.widget.ListView

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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rocketjournal.model.DataTransferObjects.ListsDTO
import com.example.rocketjournal.viewmodel.ListsViewModel
//import io.github.jan.supabase.postgrest.Postgrest.Companion.key

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListsScreen(navController: NavController, viewModel: ListsViewModel = hiltViewModel()) {
    val listsState = viewModel.listFlow.collectAsState(initial = emptyList()).value ?: emptyList()
    val isLoading = viewModel.isLoading.collectAsState(initial = true).value

    AppBackgroundGeneral {
        Column {
            BackButton(navController)
            MyListsHeader()
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else if (listsState.isEmpty()) {
                Text("No lists available", modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                LazyColumn {
                    items(listsState) { list ->
                        ListDataItemView(list = list, onCheckedChange = viewModel::updateList)
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
fun BackButton(navController: NavController) {
    Button(
        onClick = { navController.navigate("home") },
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
fun ListDataItemView(list: ListData, onCheckedChange: (ListData) -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(BorderStroke(2.dp, Color.Black), RoundedCornerShape(15.dp))
            .background(color = Color(0xFFE8D5BA), shape = RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = list.is_complete,
                onCheckedChange = { isChecked ->
                    onCheckedChange(list.copy(is_complete = isChecked))
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
        }
    }
}



