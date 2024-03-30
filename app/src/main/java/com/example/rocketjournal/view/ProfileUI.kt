package com.example.rocketjournal.view

import AppBackgroundGeneral
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Preview
@Composable
fun ProfileUI(navController: NavController) {
    AppBackgroundGeneral {

        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val maxWidth = constraints.maxWidth.toFloat()
            val maxHeight = constraints.maxHeight.toFloat()
            val circleCenter = Offset(maxWidth / (2.25.toFloat()), maxHeight / 6)

            Box(
                modifier = Modifier
                    .height(750.dp)
                    .width(350.dp)
                    .align(Alignment.Center)
                    .drawBehind {
                        drawCircle(Color(0xFFFFFFFF), radius = 80.dp.toPx(), center = circleCenter)
                    },
            )

                TextButton(onClick = { /*TODO*/ }, modifier = Modifier
                    .offset(115.dp, 300.dp)
                    .size(160.dp, 35.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent)) {
                    Text(text = "Change Profile", color = Color.Black, fontSize = 16.sp)
                }


            Column (modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 50.dp)){

                Button(onClick = { /*TODO*/ },modifier = Modifier.offset(x=10.dp),colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD6A459)
                )) {
                    Text(text = "Change Email")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { /*TODO*/ },colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD6A459))) {
                    Text(text = "Change Password")
                }
            }
        }
    }
}