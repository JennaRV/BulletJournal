package com.example.rocketjournal.view



import AppBackgroundGeneral
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController



@Composable
fun MainDashboard(navController: NavController) {

    AppBackgroundGeneral {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp

        val boxWidth = screenWidth * 0.9f
        val offsetX = screenWidth * 0.05f


        Column {
            Spacer(modifier = Modifier.size(10.dp,20.dp))
            Button(
                onClick = { /* Handle button click */ },
                modifier = Modifier
                    .offset(offsetX, screenHeight * 0.05f)
                    .size(boxWidth, 60.dp)
                    .clipToBounds()
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(35.dp)),
                shape = RoundedCornerShape(35.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF8B450)
                )
            ) {
                Text(
                    text = "Today's Date",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W800,
                    color = Color(0xFF000000),
                )
            }
            Button(
                onClick = { /* Handle button click */ },
                modifier = Modifier
                    .offset(offsetX, screenHeight * 0.1f)
                    .size(boxWidth, 150.dp)
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(20.dp)),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE8D5BA)
                ), contentPadding = PaddingValues(vertical = 0.dp)

            ){
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(15.dp))
                    .background(Color(0xFFF8B450), shape = RoundedCornerShape(15.dp))
                    .align(Alignment.Top),
                    contentAlignment = Alignment.Center)
                {
                    Text(
                        text = "Upcoming Events",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.W800,
                        color = Color(0xFF000000),
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
            Button(
                onClick = { /* Handle button click */ },
                modifier = Modifier
                    .offset(offsetX, screenHeight * 0.15f)
                    .size(boxWidth, 150.dp)
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(20.dp)),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE8D5BA)
                ), contentPadding = PaddingValues(vertical = 0.dp)

            ){
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(15.dp))
                    .background(Color(0xFFFFC167), shape = RoundedCornerShape(15.dp))
                    .align(Alignment.Top),
                    contentAlignment = Alignment.Center)
                {
                    Text(
                        text = "Upcoming Deadlines",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.W800,
                        color = Color(0xFF000000),
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
            Button(
                onClick = { /* Handle button click */ },
                modifier = Modifier
                    .offset(offsetX, screenHeight * 0.2f)
                    .size(boxWidth, 150.dp)
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(20.dp)),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE8D5BA)
                ), contentPadding = PaddingValues(vertical = 0.dp)

            ){
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(15.dp))
                    .background(Color(0xFFB98231), shape = RoundedCornerShape(15.dp))
                    .align(Alignment.Top),
                    contentAlignment = Alignment.Center)
                {
                    Text(
                        text = "On This Day",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.W800,
                        color = Color(0xFF000000),
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }



        }
    }
}


