//package com.example.rocketjournal
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.offset
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.AlertDialog
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clipToBounds
//import androidx.compose.ui.draw.drawBehind
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//
//
//@Composable
//fun TitleBackground(){
//    Column(modifier = Modifier
//        .fillMaxSize()
//        .background(Color(red = 100, green = 110, blue = 245))
//        .drawBehind {
//            drawCircle(
//                color = Color(red = 214, green = 66, blue = 105),
//                radius = 700F,
//                center = Offset(size.width / 2, size.height / 6)
//            )
//            drawCircle(
//                color = Color(red = 252, green = 228, blue = 187),
//                radius = 150F,
//                center = Offset(size.width / 2, size.height / 5)
//            )
//        },
//    ){}
//}
//
//@Composable
//fun LoginButtons(){
//    var clickLogin by remember {
//        mutableStateOf(false)
//    }
//    var clickSignup by remember {
//        mutableStateOf(false)
//    }
//    Column {
//        Box(modifier = Modifier
//            .offset(40.dp, 490.dp)
//            .size(320.dp, 40.dp)
//            .clipToBounds()
//            .background(
//                Color(red = 252, green = 228, blue = 187),
//                shape = RoundedCornerShape(60.dp)
//            )
//            .clickable { clickLogin = true }
//        ){
//            Text(
//                text = "Login",
//                fontSize = 24.sp,
//                fontWeight = FontWeight.W800,
//                color = Color(red = 100, green = 110, blue = 245),
//                modifier = Modifier.align(Alignment.Center)
//            )
//        }
//        Box(
//            modifier = Modifier
//                .offset(40.dp, 510.dp)
//                .size(320.dp, 40.dp)
//                .clipToBounds()
//                .clickable { clickSignup = true }
//                .background(
//                    Color(red = 252, green = 228, blue = 187),
//                    shape = RoundedCornerShape(60.dp)
//                )
//        ) {
//            Text(
//                text = "Sign Up",
//                fontSize = 24.sp,
//                fontWeight = FontWeight.W800,
//                color = Color(red = 100, green = 110, blue = 245),
//                modifier = Modifier.align(Alignment.Center)
//            )
//        }
//    }
//    if(clickLogin) {
//        clickSignup = false
//        AlertDialog(
//
//            onDismissRequest = {
//                // Dismiss the popup dialog when click outside
//                clickLogin = false
//            },
//            title = {
//                Text(text = "Login Test",fontWeight = FontWeight.ExtraBold)
//            },
//            text = {
//                Text(text = "GO MAKE LOGIN NAV",fontWeight = FontWeight.ExtraBold)
//            },
//            confirmButton = {
//                Button(
//                    onClick = {
//                        // Dismiss the popup dialog when click button
//                        clickLogin = false
//                    }
//                ) {
//                    Text(text = "No")
//                }
//            }
//        )
//    }
//    if(clickSignup){
//        clickLogin = false
//        AlertDialog(
//            onDismissRequest = {
//                clickSignup = false
//            },
//            title = {
//                Text(text = "SignUp Test", fontWeight = FontWeight.ExtraBold)
//            },
//            text = {
//                Text(text = "GO MAKE SIGNUP NAV",fontWeight = FontWeight.ExtraBold)
//            },
//            confirmButton = {
//                Button(
//                    onClick = {
//                        clickSignup = false
//                    }
//                ) {
//                    Text(text = "No")
//                }
//            }
//        )
//    }
//
//
//}