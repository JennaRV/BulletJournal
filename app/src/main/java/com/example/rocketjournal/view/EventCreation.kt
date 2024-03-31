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


@Composable
fun EventCreation(navController: NavController) {
    AppBackgroundGeneral {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp

        val boxWidth = screenWidth * 0.9f
        val offsetX = screenWidth * 0.05f
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
                // Title text aligned to the left
                TitleText(title = "Details")

                // Spacer to push settings button to the right
                //Spacer(modifier = Modifier.weight(1f))

                // Settings button aligned to the right
                CancelButton(navController)
            }
            DateButton()
            Spacer(modifier = Modifier.size(10.dp))
            TimeButton()
            Spacer(modifier = Modifier.size(10.dp))
            AddEntryButton()
        }
    }
}



@Composable
fun TitleText(title: String) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .background(
                color = Color.White.copy(alpha = 0.0f),
                shape = RoundedCornerShape(15.dp),
            )
            .padding(16.dp),
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
            .padding(horizontal = 16.dp, vertical = 8.dp)
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
fun DateButton() {
    Button(
        onClick = { },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(Color.White.copy(alpha = 0.5f)),

        modifier = Modifier
            .background(Color.White.copy(alpha = 0.0f), shape = RoundedCornerShape(10.dp))
            .padding(0.dp)
            .fillMaxWidth()

    ) {
        Text(
            "Date",
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

@Composable
fun TimeButton() {
    Button(
        onClick = { },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(Color.White.copy(alpha = 0.5f)),

        modifier = Modifier
            .background(Color.White.copy(alpha = 0.0f), shape = RoundedCornerShape(10.dp))
            .padding(0.dp)
            .fillMaxWidth()

    ) {
        Text(
            "Time",
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

@Composable
fun AddEntryButton() {
    Button(
        onClick = { },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(Color.White.copy(alpha = 0.5f)),

        modifier = Modifier
            .background(Color.White.copy(alpha = 0.0f), shape = RoundedCornerShape(10.dp))
            .padding(0.dp)
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

//    val primaryColor = Color(0xFF606BD1)
//    val secondaryColor = Color(0xFFBA355D)
//    Column(modifier = Modifier
//            .background(primaryColor)
//            .fillMaxSize()
//            .drawBehind {
//                drawCircle(
//                        color = secondaryColor,
//                        radius = 700f,
//                        center = Offset(size.width / 2, size.height / 6)
//                )
//                drawRect(
//                        color = Color.White.copy(alpha = 0.5f),
//                        topLeft = Offset(0f, size.height/30),
//
//                )
//            }
//    ) {
//        Column (
//                modifier = Modifier
//                        .fillMaxSize()
//        ) {
//            Row(
//                    modifier = Modifier
//                            .padding(35.dp)
//            ) {
//                Text(
//                        "Details",
//                        modifier = Modifier
//                                .fillMaxWidth(),
//                        //fontWeight = FontWeight.Bold,
//                        style = TextStyle(
//                                color = Color.White,
//                                fontSize = 25.sp,
//                                textAlign = TextAlign.Center
//                        )
//                )
//
//            }
//            Row(
//                    modifier = Modifier
//                            .padding(16.dp),
//
//                    ) {
//                Button(
//                        onClick = { },
//                        shape = RoundedCornerShape(10.dp),
//                        colors = ButtonDefaults.buttonColors(Color.White.copy(alpha = 0.5f)),
//
//                        modifier = Modifier
//                                .background(Color.White.copy(alpha = 0.0f), shape = RoundedCornerShape(10.dp))
//                                .padding(0.dp)
//                                .fillMaxWidth()
//
//                ) {
//                    Text(
//                            "Date",
//                            modifier = Modifier
//                                    .fillMaxWidth(),
//                            fontWeight = FontWeight.Bold,
//                            style = TextStyle(
//                                    color = Color.White,
//                                    fontSize = 25.sp,
//                                    textAlign = TextAlign.Center
//                            )
//                    )
//                }
//            }
//            Row(
//                    modifier = Modifier
//                            .padding(16.dp),
//
//                    ) {
//                Button(
//                        onClick = { },
//                        shape = RoundedCornerShape(10.dp),
//                        colors = ButtonDefaults.buttonColors(Color.White.copy(alpha = 0.5f)),
//
//                        modifier = Modifier
//                                .background(Color.White.copy(alpha = 0.0f), shape = RoundedCornerShape(10.dp))
//                                .padding(0.dp)
//                                .fillMaxWidth()
//
//                ) {
//                    Text(
//                            "Time",
//                            modifier = Modifier
//                                    .fillMaxWidth(),
//                            fontWeight = FontWeight.Bold,
//                            style = TextStyle(
//                                    color = Color.White,
//                                    fontSize = 25.sp,
//                                    textAlign = TextAlign.Center
//                            )
//                    )
//                }
//            }
//            Row(
//                    modifier = Modifier
//                            .padding(16.dp),
//
//                    ) {
//                Button(
//                        onClick = { },
//                        shape = RoundedCornerShape(10.dp),
//                        colors = ButtonDefaults.buttonColors(Color.White.copy(alpha = 0.5f)),
//
//                        modifier = Modifier
//                                .background(Color.White.copy(alpha = 0.0f), shape = RoundedCornerShape(10.dp))
//                                .padding(0.dp)
//                                .fillMaxWidth(0.5f)
//
//                ) {
//                    Text(
//                            "Add Entry",
//                            modifier = Modifier
//                                    .fillMaxWidth(),
//                            fontWeight = FontWeight.Bold,
//                            style = TextStyle(
//                                    color = Color.White,
//                                    fontSize = 25.sp,
//                                    textAlign = TextAlign.Center
//                            )
//                    )
//                }
//            }
//        }
//    }
