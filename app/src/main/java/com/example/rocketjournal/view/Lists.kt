package com.example.rocketjournal.view

import AppBackgroundGeneral

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.rocketjournal.model.network.SupabaseClient
import com.example.rocketjournal.viewmodel.ListsViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.ui.unit.dp


@Composable
fun ListsScreen(navController: NavController, viewModel: ListsViewModel) {
    // Assuming viewModel.lists is a StateFlow or LiveData holding your lists
    //val lists by viewModel.getLists()

    AppBackgroundGeneral {
        Column {
            BackButton(navController)
            MyListsHeader()
            LazyColumn {
                item {
                    // Call your List composable here to display an empty list box
                    List()
                    List()
                    List()
                    List()
                    List()
                    List()
                    List()
                    List()
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
@Preview
fun List(){
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp) // Adjust padding as needed
            .border(
                BorderStroke(2.dp, Color.Black),
                shape = RoundedCornerShape(15.dp)
            ) // Border with rounded corners
            .background(
                color = Color(0xFFE8D5BA),
                shape = RoundedCornerShape(15.dp),
            ) // Background color inside the border
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp), // Padding inside the border for the content

        contentAlignment = Alignment.Center
    ) {

    }
}
