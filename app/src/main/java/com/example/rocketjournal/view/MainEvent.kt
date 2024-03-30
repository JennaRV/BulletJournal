package com.example.test

import AppBackgroundGeneral
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rocketjournal.model.dataModel.EventData
import com.example.rocketjournal.view.BottomNavigationBar
import com.example.rocketjournal.view.HeaderRow
import com.example.rocketjournal.viewmodel.EventViewModel


//@Preview
@Composable
fun MainEvent(navController: NavController, viewModel: EventViewModel = hiltViewModel()) {
//    val primaryColor = Color(0xFF606BD1)
//    val secondaryColor = Color(0xFFBA355D)
//    val darkColor = Color(0xFFB98231)
//    val lightColor = Color(0xFFE8D5BA)

    val entriesState = viewModel.eventList.collectAsState(initial = emptyList()).value ?: emptyList()
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

            //Event Creation
            Button(
                onClick = { navController.navigate("home") },
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .border(
                        BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(60.dp),

                    )
                    .height(50.dp)
                    .align(Alignment.End),
                shape = RoundedCornerShape(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB98231)
                )
            ) {
                Text(
                    text = "Add Entry",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.size(20.dp))

            LazyColumn {
                item {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    } else if (entriesState.isEmpty()) {
                        Text(
                            text = "No Events Available",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }
                items(entriesState) { eventEntry ->
                    EventDataItemView(event = eventEntry)

                }
            }

            BottomNavigationBar(navController = navController)
        }
    }
}

@Composable
fun EventDataItemView(event: EventData) {
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
        Column{
            Text(text = event.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Text(text = event.details, maxLines = 2)
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






//    Column(modifier = Modifier
//        .background(primaryColor)
//        .fillMaxSize()
//        .drawBehind {
//            drawCircle(
//                color = secondaryColor,
//                radius = 700f,
//                center = Offset(size.width / 2, size.height / 6)
//            )
//        }
//    ) {
//        Column (modifier = Modifier
//            .fillMaxSize()) {
//            Row(modifier = Modifier
//                .padding(16.dp)
//            ) {
//                Button(
//                    onClick = { },
//                    shape = RoundedCornerShape(10.dp),
//                    colors = ButtonDefaults.buttonColors(darkColor),
//                    modifier = Modifier
//                        .background(darkColor, shape = RoundedCornerShape(10.dp))
//                        .padding(0.dp)
//                        .fillMaxWidth(.30f)
//                ) {
//                    Text(
//                        "<-------",
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        fontWeight = FontWeight.Bold,
//                        style = TextStyle(
//                            color = Color.Black,
//                            fontSize = 14.sp,
//                            textAlign = TextAlign.Center
//                        )
//                    )
//                }
//
//            }
//            Row(
//                modifier = Modifier
//                    .padding(16.dp)
//            ) {
//                Row(
//                    modifier = Modifier
//                        .background(darkColor, shape = RoundedCornerShape(10.dp))
//                        .padding(12.dp)
//                        .fillMaxWidth()
//                ) {
//                    Text(
//                        "My Events",
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        fontWeight = FontWeight.Bold,
//                        style = TextStyle(
//                            color = Color.Black,
//                            fontSize = 25.sp,
//                            textAlign = TextAlign.Center
//                        )
//                    )
//
//                }
//            }
//            Row(
//                modifier = Modifier
//                    .padding(16.dp),
//                horizontalArrangement = Arrangement.End
//            ) {
//                Button(
//                    onClick = { },
//                    shape = RoundedCornerShape(10.dp),
//                    colors = ButtonDefaults.buttonColors(darkColor),
//
//                    modifier = Modifier
//                        .background(darkColor, shape = RoundedCornerShape(10.dp))
//                        .padding(0.dp)
//                        .fillMaxWidth(.30f)
//
//                ) {
//                    Text(
//                        "Add Entry",
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        fontWeight = FontWeight.Bold,
//                        style = TextStyle(
//                            color = Color.Black,
//                            fontSize = 14.sp,
//                            textAlign = TextAlign.Center
//                        )
//                    )
//                }
//            }
//            Row(
//                modifier = Modifier
//                    .padding(16.dp)
//            ) {
//                Row(
//                    modifier = Modifier
//                        .background(lightColor, shape = RoundedCornerShape(10.dp))
//                        .padding(40.dp)
//                        .fillMaxWidth()
//                ){
//                    Row(
//                        modifier = Modifier
//                            .background(lightColor, shape = RoundedCornerShape(10.dp))
//                            .padding(10.dp)
//                            .fillMaxWidth()
//                    ) {
//                        Text (
//                            "Event Name",
//                            modifier = Modifier
//                                .fillMaxWidth(),
//                            fontWeight = FontWeight.Bold,
//                            style = TextStyle(
//                                color = Color.Black,
//                                fontSize = 25.sp,
//                                textAlign = TextAlign.Center
//                            )
//                        )
//
//                    }
//                }
//            }
//            Row(
//                modifier = Modifier
//                    .padding(16.dp)
//            ) {
//                Row(
//                    modifier = Modifier
//                        .background(lightColor, shape = RoundedCornerShape(10.dp))
//                        .padding(40.dp)
//                        .fillMaxWidth()
//                ){
//                    Row(
//                        modifier = Modifier
//                            .background(lightColor, shape = RoundedCornerShape(10.dp))
//                            .padding(10.dp)
//                            .fillMaxWidth()
//                    ) {
//                        Text (
//                            "Event Name",
//                            modifier = Modifier
//                                .fillMaxWidth(),
//                            fontWeight = FontWeight.Bold,
//                            style = TextStyle(
//                                color = Color.Black,
//                                fontSize = 25.sp,
//                                textAlign = TextAlign.Center
//                            )
//                        )
//
//                    }
//                }
//            }
//            Row(
//                modifier = Modifier
//                    .padding(16.dp)
//            ) {
//                Row(
//                    modifier = Modifier
//                        .background(lightColor, shape = RoundedCornerShape(10.dp))
//                        .padding(40.dp)
//                        .fillMaxWidth()
//                ){
//                    Row(
//                        modifier = Modifier
//                            .background(lightColor, shape = RoundedCornerShape(10.dp))
//                            .padding(10.dp)
//                            .fillMaxWidth()
//                    ) {
//                        Text (
//                            "Event Name",
//                            modifier = Modifier
//                                .fillMaxWidth(),
//                            fontWeight = FontWeight.Bold,
//                            style = TextStyle(
//                                color = Color.Black,
//                                fontSize = 25.sp,
//                                textAlign = TextAlign.Center
//                            )
//                        )
//
//                    }
//                }
//            }
//        }
//
//    }



