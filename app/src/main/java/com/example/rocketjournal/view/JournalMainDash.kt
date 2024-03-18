package com.example.rocketjournal.view

import AppBackgroundGeneral
import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController




@Preview
@Composable
fun JournalMainDash() {
    AppBackgroundGeneral {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp

        val boxWidth = screenWidth * 0.9f
        val offsetX = screenWidth * 0.05f

        Column {

            //A back button
            Row {
                BackButton()  //Add navController = navController Later
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
                    text = "Journals",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }


            Spacer(modifier = Modifier.size(20.dp))

            //Small Button for "New Entries"
            Button(
                onClick = { /* Handle button click */ },
                modifier = Modifier
                    .padding(horizontal = 40.dp, vertical = 3.dp)
                    .offset(offsetX, screenHeight * 0.005f)
                    .size(95.dp, 40.dp)
                    .clipToBounds()
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(15.dp))
                    .shadow(10.dp, RoundedCornerShape(15.dp))
                    .align(Alignment.End),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB98231)
                )
            ) {
                Text(
                    text = "New Entries",
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            // NEW ENTRIES WILL GO HERE
            JournalEntriesList()



            Spacer(modifier = Modifier.size(20.dp))
        }
    }
}

@Composable
fun BackButton() {
    //insert (navController: NavController) as a parameter to use this function
    Button(
        onClick = {/* Handle button click */}, // navController.navigate("home") or something
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp) // Adjust padding as needed
            .border(
                BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(25.dp)
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
                .size(width = 70.dp, height = 30.dp), // or any size you prefer
            Color.Black
        )
    }
}

@Composable
fun SettingsButton(
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            //.size(48.dp) // Adjust size as needed
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Settings",
            modifier = Modifier
                .size(width = 70.dp, height = 30.dp),
            tint = Color(0xFF646EF5) // Purple color (red = 100, green = 110, blue = 245)
        )
    }
}


//Dataclass that will hold a journal entry
data class JournalEntry(
    val entryID : Int,
    val journalID: Int,
    val title: String,
    val date: String,
    val content: String
)

//This is a function that will fetch all Journals from the database, if user has created any
fun fetchJournalEntries(): List<JournalEntry>{

    //Connects to database here?

    return TODO("Provide the return value")
}

@Composable
fun JournalEntriesList() {
    val journalEntries = remember { mutableStateListOf<JournalEntry>() }

    LaunchedEffect(Unit) {
        // Fetch journal entries from Supabase
        val entries = fetchJournalEntries()
        // Update the state with fetched entries
        journalEntries.addAll(entries)
    }

    LazyColumn {
        items(journalEntries) { entry ->
            JournalEntryItem(entry)
        }
    }
}

@Composable
fun JournalEntryItem(entry: JournalEntry) {
    // Composable to display a single journal entry
    // You can customize this as per your design

    //Contains the title, date, and content of the journal entry
    //in a box that looks like a button
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFFB98231), shape = RoundedCornerShape(15.dp))
            .clickable { /* Handle click on journal entry */ }
            .border(1.dp, Color.Black, shape = RoundedCornerShape(15.dp))
            .padding(16.dp)
    ) {
        Column {
            Text(text = entry.title)
            Text(text = entry.date)
            Text(text = entry.content)
        }
    }
}

