package com.example.rocketjournal.view.Journal


import AppBackgroundGeneral
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rocketjournal.model.dataModel.JournalEntryData
import com.example.rocketjournal.view.BackButton
import com.example.rocketjournal.viewmodel.JournalEntryViewModel

//import com.example.rocketjournal.view.Journal.


//@Preview
@SuppressLint("SuspiciousIndentation")
@Composable
fun JournalEntry(
    navController: NavController,
    viewModel: JournalEntryViewModel = hiltViewModel(),
    journalEntryID: Int) {


    AppBackgroundGeneral {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp

        val boxWidth = screenWidth * 0.9f
        val offsetX = screenWidth * 0.05f

        Column {

            //A back button
            Row {
                BackButton(navController = navController,"journal")  //Add navController = navController Later
                Spacer(modifier = Modifier.weight(1f))
                SettingsButton(onClick = { /* Handle settings button click */ })


            }

            Spacer(modifier = Modifier.size(20.dp))




        }
    }
}