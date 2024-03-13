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
fun MainEvent() {
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
        }
    ) {
        Column (modifier = Modifier
            .fillMaxSize()) {
            Row(modifier = Modifier
                .padding(16.dp)
            ) {
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(darkColor),
                    modifier = Modifier
                        .background(darkColor, shape = RoundedCornerShape(10.dp))
                        .padding(0.dp)
                        .fillMaxWidth(.30f)
                ) {
                    Text(
                        "<-------",
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                }

            }
            Row(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .background(darkColor, shape = RoundedCornerShape(10.dp))
                        .padding(12.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        "My Events",
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center
                        )
                    )

                }
            }
            Row(
                modifier = Modifier
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(darkColor),

                    modifier = Modifier
                        .background(darkColor, shape = RoundedCornerShape(10.dp))
                        .padding(0.dp)
                        .fillMaxWidth(.30f)

                ) {
                    Text(
                        "Add Entry",
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .background(lightColor, shape = RoundedCornerShape(10.dp))
                        .padding(40.dp)
                        .fillMaxWidth()
                ){
                    Row(
                        modifier = Modifier
                            .background(lightColor, shape = RoundedCornerShape(10.dp))
                            .padding(10.dp)
                            .fillMaxWidth()
                    ) {
                        Text (
                            "Event Name",
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 25.sp,
                                textAlign = TextAlign.Center
                            )
                        )

                    }
                }
            }
            Row(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .background(lightColor, shape = RoundedCornerShape(10.dp))
                        .padding(40.dp)
                        .fillMaxWidth()
                ){
                    Row(
                        modifier = Modifier
                            .background(lightColor, shape = RoundedCornerShape(10.dp))
                            .padding(10.dp)
                            .fillMaxWidth()
                    ) {
                        Text (
                            "Event Name",
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 25.sp,
                                textAlign = TextAlign.Center
                            )
                        )

                    }
                }
            }
            Row(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .background(lightColor, shape = RoundedCornerShape(10.dp))
                        .padding(40.dp)
                        .fillMaxWidth()
                ){
                    Row(
                        modifier = Modifier
                            .background(lightColor, shape = RoundedCornerShape(10.dp))
                            .padding(10.dp)
                            .fillMaxWidth()
                    ) {
                        Text (
                            "Event Name",
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 25.sp,
                                textAlign = TextAlign.Center
                            )
                        )

                    }
                }
            }
        }

    }
}
