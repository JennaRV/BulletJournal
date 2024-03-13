package com.example.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
//import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview
@Composable
fun EventCreation() {
    val primaryColor = Color(0xFF606BD1)
    val secondaryColor = Color(0xFFBA355D)
    val darkColor = Color(0xFFB98231)
    val lightColor = Color(0xFFE8D5BA)
    Column(modifier = Modifier
            .background(primaryColor)
            .fillMaxSize()
            .drawBehind {
                drawCircle(
                        color = secondaryColor,
                        radius = 700f,
                        center = Offset(size.width / 2, size.height / 6)
                )
                drawRect(
                        color = Color.White.copy(alpha = 0.5f),
                        topLeft = Offset(0f, size.height/30),

                )
            }
    ) {
        Column (
                modifier = Modifier
                        .fillMaxSize()
        ) {
            Row(
                    modifier = Modifier
                            .padding(35.dp)
            ) {
                Text(
                        "Details",
                        modifier = Modifier
                                .fillMaxWidth(),
                        //fontWeight = FontWeight.Bold,
                        style = TextStyle(
                                color = Color.White,
                                fontSize = 25.sp,
                                textAlign = TextAlign.Center
                        )
                )

            }
            Row(
                    modifier = Modifier
                            .padding(16.dp),

                    ) {
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
            Row(
                    modifier = Modifier
                            .padding(16.dp),

                    ) {
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
            Row(
                    modifier = Modifier
                            .padding(16.dp),

                    ) {
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
        }
    }
}