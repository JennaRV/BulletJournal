package com.example.test

//import com.example.rocketjournal.view.BottomNavigationBar
//import com.example.rocketjournal.view.HeaderRow
import AppBackgroundGeneral
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rocketjournal.model.dataModel.EventData
import com.example.rocketjournal.viewmodel.EventViewModel


//@Preview
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainEvent(navController: NavController, viewModel: EventViewModel = hiltViewModel()) {

    val eventState = viewModel.eventList.collectAsState(initial = emptyList()).value ?: emptyList()
    val isLoading = viewModel.isLoading.collectAsState(initial = true).value

    AppBackgroundGeneral {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp

        val boxWidth = screenWidth * 0.9f
        val offsetX = screenWidth * 0.05f

        Column {
            BackButton(navController)
            HeaderRow()

            Spacer(modifier = Modifier.size(20.dp))

            //Create an Event
//            Button(
//                onClick = { navController.navigate("newEvent") },
//                modifier = Modifier
//                    .padding(horizontal = 16.dp, vertical = 8.dp)
//                    .border(
//                        BorderStroke(1.dp, Color.Black),
//                        shape = RoundedCornerShape(60.dp),
//
//                    )
//                    .height(50.dp)
//                    .align(Alignment.End),
//                shape = RoundedCornerShape(60.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFFB98231)
//                )
//            ) {
//                Text(
//                    text = "Add Entry",
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Black
//                )
//            }
            EventCreation(navController)

            Spacer(modifier = Modifier.size(20.dp))

            //This is where the events go
//            LazyColumn {
//                item {
//                    if (isLoading) {
//                        CircularProgressIndicator(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(vertical = 16.dp)
//                                .align(Alignment.CenterHorizontally)
//                        )
//                    } else if (eventState.isEmpty()) {
//                        Text(
//                            text = "No Events Available",
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(vertical = 16.dp)
//                                .align(Alignment.CenterHorizontally)
//                        )
//                    }
//                }
////                items(eventState) { eventEntry ->
////                    EventDataItemView(event = eventEntry
////                    onEventClicked = { clickedEvent ->
////                        navController.navigate("event_list/${clickedEvent.event_id}")
////                    },
////                    onDeleteClicked = {
////                        viewModel.deleteEvent(eventEntry)
////                    }
////
////                }
//            }

//            BottomNavigationBar(navController = navController)
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else if (eventState.isEmpty()) {
                Text("No Events available", modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                LazyColumn {
                    items(eventState) { event ->
                        EventDataItemView(
                            event = event,
                            onEventClicked = { clickedEvent ->
                                navController.navigate("event_list/${clickedEvent.event_id}")
                            },
//                            onDeleteClicked = {
//                                viewModel.deleteEvent(event)
//                            }

                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EventDataItemView(event: EventData,
                      onEventClicked: (EventData) -> Unit)
                      //onDeleteClicked: (EventData) -> Unit)
{

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(BorderStroke(2.dp, Color.Black), RoundedCornerShape(15.dp))
            .background(color = Color(0xFFE8D5BA), shape = RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp)
            .clickable {
                onEventClicked(event)

            },
        contentAlignment = Alignment.Center
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = event.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Text(text = event.details, maxLines = 2)
//            Icon(
//                imageVector = Icons.Default.Delete,
//                contentDescription = "Delete",
//                tint = Color.Black, // Adjust the color as needed
//                modifier = Modifier
//                    .clickable {
//                        onDeleteClicked(event) // Invoke the delete callback
//                    }
//            )
        }
    }
}

@Composable
fun HeaderRow() {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .border(
                BorderStroke(2.dp, Color.Black),
                shape = RoundedCornerShape(15.dp)
            )
            .background(
                color = Color(0xFFB98231),
                shape = RoundedCornerShape(15.dp),
            )
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Events",
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
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(
                BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(60.dp),
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
                .size(width = 70.dp, height = 30.dp),
            Color.Black
        )
    }
}


