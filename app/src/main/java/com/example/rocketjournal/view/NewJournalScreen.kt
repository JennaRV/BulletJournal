package com.example.rocketjournal.view

import AppBackgroundGeneral
import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
//Import JournalMainDash
import com.example.rocketjournal.view.JournalMainDash




//@Preview
@Composable
fun NewJournalScreen(navController: NavController) {
    AppBackgroundGeneral {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp

        val boxWidth = screenWidth * 0.9f
        val offsetX = screenWidth * 0.05f

        Column {

            //A back button
            Row {
                BackButton(navController = navController)  //Add navController = navController Later
                Spacer(modifier = Modifier.weight(1f))
                SettingsButton(onClick = { /* Handle settings button click */ })

            }

            Spacer(modifier = Modifier.size(20.dp))

            //A header that looks like a button that says Journals
            Button(
                onClick = { /* Handle button click */ },
                modifier = Modifier
                    .offset(offsetX, screenHeight * 0.0012f)
                    .size(boxWidth, 60.dp)
                    .clipToBounds()
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(15.dp))
                    .shadow(10.dp, RoundedCornerShape(15.dp)),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB98231)
                )

            ) {
                Text(
                    text = "Details",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }


            Spacer(modifier = Modifier.size(20.dp))

            var title by remember { mutableStateOf(TextFieldValue()) }
            var content by remember { mutableStateOf(TextFieldValue()) }

            //Small Button for "New Entries"Column(
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp)
                )

                TextField(
                    value = content,
                    onValueChange = { content = it },
                    label = { Text("Content") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f), // Expand as the user continues writing
                    maxLines = Int.MAX_VALUE,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                        //maxLines = Int.MAX_VALUE
                    ),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = MaterialTheme.typography.bodySmall.fontSize
                    ),
                    shape = RoundedCornerShape(15.dp)
                )

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .offset(offsetX * 0.0012f, screenHeight * 0.0012f)
                        .size(boxWidth, 60.dp)
                        .border(1.dp, Color.Black, shape = RoundedCornerShape(15.dp))
                        .shadow(10.dp, RoundedCornerShape(15.dp))
                        .align(CenterHorizontally),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFB98231)
                    )) {
                        Text(
                        text = "Save",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                        )
                    }




            }

        }
    }
}


//@OptIn(ExperimentalMaterial3Api::class)
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Preview
//@Composable
//fun JournalCreationScreen(/*navController: NavController*/) {
//    AppBackgroundGeneral {
//        var title by remember { mutableStateOf(TextFieldValue()) }
//        var content by remember { mutableStateOf(TextFieldValue()) }
//
//        Scaffold(
//            topBar = {
//                TopAppBar(
//                    title = { Text(text = "Details") },
//                    actions = {
//                        IconButton(onClick = { /* Handle save button click */ }) {
//                            Icon(Icons.Default.Create,
//                                contentDescription = "Save")
//                        }
//                    },
//                    navigationIcon = {
//                        IconButton(onClick = { /* Handle back button click */ }) {
//                            Icon(Icons.Default.ArrowBack,
//                                contentDescription = "Back")
//                        }
//                    }
//                )
//            }
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(16.dp),
//                verticalArrangement = Arrangement.spacedBy(16.dp)
//            ) {
//                TextField(
//                    value = title,
//                    onValueChange = { title = it },
//                    label = { Text("Title") },
//                    singleLine = true,
//                    modifier = Modifier.fillMaxWidth()
//                )
//
//                TextField(
//                    value = content,
//                    onValueChange = { content = it },
//                    label = { Text("Content") },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .weight(1f), // Expand as the user continues writing
//                    maxLines = Int.MAX_VALUE,
//                    keyboardOptions = KeyboardOptions.Default.copy(
//                        imeAction = ImeAction.Done,
//                        keyboardType = KeyboardType.Text
//                        //maxLines = Int.MAX_VALUE
//                    ),
//                    textStyle = LocalTextStyle.current.copy(
//                        fontSize = MaterialTheme.typography.bodySmall.fontSize
//                    )
//                )
//            }
//        }
//    }
//}




