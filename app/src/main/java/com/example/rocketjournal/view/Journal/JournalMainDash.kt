package com.example.rocketjournal.view.Journal

import AppBackgroundGeneral
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rocketjournal.model.dataModel.JournalData
import com.example.rocketjournal.model.dataModel.JournalEntryData
import com.example.rocketjournal.model.dataModel.ListData

import com.example.rocketjournal.viewmodel.JournalEntryViewModel
import java.time.format.DateTimeFormatter
import com.example.rocketjournal.view.Journal.JournalEntry as JournalEntry

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JournalMainDash(navController: NavController, viewModel: JournalEntryViewModel = hiltViewModel()) {

//    val entriesState = viewModel.journalList.collectAsState(initial = emptyList()).value ?: emptyList()
//    val isLoading = viewModel.isLoading.collectAsState(initial = true).value

    val entriesState = viewModel.entryList.collectAsState(initial = emptyList()).value ?: emptyList()
    val isLoading = viewModel.isLoading.collectAsState(initial = true).value


    AppBackgroundGeneral {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp

        val boxWidth = screenWidth * 1f
        val offsetX = screenWidth * 0.05f




        Column {



            HeaderRow(title = "Journals", onSettingsClick = { /* Handle settings click */ })

            Spacer(modifier = Modifier.size(20.dp))

            //A header that looks like a button that says Journals
//            Button(
//                onClick = {  navController.navigate("newJournal")  },
//                modifier = Modifier
//                    .offset(offsetX, screenHeight * 0.0012f)
//                    .size(boxWidth, 60.dp)
//                    .clipToBounds()
//                    .border(1.dp, Color.Black, shape = RoundedCornerShape(15.dp))
//                    .shadow(10.dp, RoundedCornerShape(15.dp)),
//                shape = RoundedCornerShape(15.dp),
//                colors = ButtonDefaults.buttonColors(w
//                    containerColor = Color(0xFFB98231)
//                )
//
//            ) {
//                Text(
//                    text = "New Entry",
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Black
//                )
//            }
            CreateJournalEntry()


            Spacer(modifier = Modifier.size(20.dp))


            // NEW ENTRIES WILL GO HERE
            //JournalEntriesList()

            //PlaceholderEntry()

            LazyColumn (modifier = Modifier .size(boxWidth, 450.dp)){
                item {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                                .align(Alignment.CenterHorizontally)
                                .testTag("progress")
                        )
                    } else if (entriesState.isEmpty()) {
                        Text(
                            text = "No Entries Available",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }
                items(entriesState) { journalEntry ->
//                    JournalDataItemView(journal = journal)
                    JournalEntryDataItemView(journal = journalEntry,
                        onEntryClicked = { clickedEntry ->
                            // Handle navigation or any other action here
                            // For example, navigate to another screen
                            navController.navigate("task_list/${clickedEntry.entry_id}")
                        },
                        onDeleteClicked = {
                            viewModel.deleteJournalEntry(journalEntry)
                        })
                }


            }


            //PlaceholderEntryList(navController =navController)


        }
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


@Composable
fun JournalDataItemView(journal: JournalData) {


    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(BorderStroke(2.dp, Color.Black), RoundedCornerShape(15.dp))
            .background(color = Color(0xFFE8D5BA), shape = RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp)
            .clickable { /* Handle click event here if needed */ }
    ) {
        Column {
            // Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Journal ID: " + journal.journal_id.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text("User ID: " + journal.user_id.toString())

        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun JournalEntryDataItemView(
    journal: JournalEntryData,
    onEntryClicked: (JournalEntryData) -> Unit,
    onDeleteClicked: (JournalEntryData) -> Unit,) {

    val formattedDate = journal.created_at?.let { formatDate(it) }

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(BorderStroke(2.dp, Color.Black), RoundedCornerShape(15.dp))
            .background(color = Color(0xFFE8D5BA), shape = RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp)
            .clickable {
                /* Handle click event here if needed */

            },
        contentAlignment = Alignment.Center
    ) {


        Column {
            //Text that says 'Title of entry' with a bold header font
            Text(text = "$formattedDate", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            //Text that has the lorem ipsum content of the entry that only desplays a few lines
            Text(text = "${journal.content}", maxLines = 3)


        }
    }
}

@Preview
@Composable
fun PlaceholderEntry() {
    // A box to represent a placeholder journal Entry
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(BorderStroke(2.dp, Color.Black), RoundedCornerShape(15.dp))
            .background(color = Color(0xFFE8D5BA), shape = RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp),
//        contentAlignment = Alignment.Center
    ) {
        Column {
            //Text that says 'Title of entry' with a bold header font
            Text(text = "Title of Entry", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            //Text that has the lorem ipsum content of the entry that only desplays a few lines
            Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sagittis tincidunt sapien at facilisis. Sed aliquam sem sed erat sagittis maximus. Donec non tortor purus. Aliquam non pretium diam. Nulla ullamcorper enim vel dui dictum, ac auctor magna finibus. Sed hendrerit est vitae sapien gravid", maxLines = 3)


        }
    }

}

@Composable
fun PlaceholderEntryList(navController: NavController){
    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp) // Adjust the value as needed

        ){
            items(8) {
                PlaceholderEntry()
            }
        }


    }



}


@Composable
fun TitleText(title: String) {
    Text(
        text = "$title",
        color = Color.White,
        style = MaterialTheme.typography.displaySmall,
        modifier = Modifier.padding(start = 16.dp) // Adjust padding as needed
    )
}


@Composable
fun HeaderRow(title: String, onSettingsClick: () -> Unit) {
    //Small Spacer
    Spacer(modifier = Modifier.size(10.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Title text aligned to the left
        TitleText(title = title)

        // Spacer to push settings button to the right
        Spacer(modifier = Modifier.weight(1f))

        // Settings button aligned to the right
        SettingsButton(onClick = onSettingsClick)
    }
}


// Function to format the date string
@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(dateTime: kotlinx.datetime.LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return dateTime.month.toString() + " " + dateTime.dayOfMonth.toString() + ", " + dateTime.year.toString()

}