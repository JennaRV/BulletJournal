package com.example.rocketjournal.view

//@SuppressLint("SuspiciousIndentation")
import AppBackgroundGeneral
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rocketjournal.model.DataTransferObjects.EventDTO
import com.example.rocketjournal.viewmodel.EventViewModel
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@Composable
fun EventView(navController: NavController,
              eventID : Int,
              eventViewModel: EventViewModel = hiltViewModel())
{

    val coroutine = rememberCoroutineScope()
    var event by remember { mutableStateOf<EventDTO?>(null) }

    LaunchedEffect(Unit) {
        coroutine.launch {
            event = eventViewModel.getEvents(eventID)

        }
    }
    AppBackgroundGeneral {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp

        val boxWidth = screenWidth * 0.9f
        val offsetX = screenWidth * 0.05f

        Column {

            //A back button
            Row {
                BackButton(
                    navController = navController,
                    "event"
                )  //Add navController = navController Later
                Spacer(modifier = Modifier.weight(1f))


                event?.let { EventViewHeader(it.name) }



            }

            Spacer(modifier = Modifier.size(20.dp))


        }
    }
}

@Composable
fun EventViewHeader(eventName: String, eventViewModel: EventViewModel = hiltViewModel()) {

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
            text = eventName,
            fontSize = 24.sp,
            fontWeight = FontWeight.W800,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}