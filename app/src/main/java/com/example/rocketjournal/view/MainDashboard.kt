package com.example.rocketjournal.view



import AppBackgroundGeneral
import android.graphics.drawable.Icon
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.rocketjournal.model.dataModel.JournalEntryData
import com.example.rocketjournal.view.Journal.JournalEntryDataItemView
import com.example.rocketjournal.viewmodel.EventViewModel
import com.example.rocketjournal.viewmodel.JournalEntryViewModel
import com.example.rocketjournal.viewmodel.JournalViewModel
import com.example.rocketjournal.viewmodel.ListsViewModel
import dagger.Component
import kotlinx.coroutines.launch


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
                    containerColor = Color(0xFFB98231)
                )
            ) {
                Text(
                    text = "Welcome!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W800,
                    color = Color(0xFF000000),
                )
            }
            SingleComponent()
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SingleComponent() {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val boxWidth = screenWidth * 0.9f
    val offsetX = screenWidth * 0.05f
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 1, pageCount = {
        3
    })

    val contentState = mutableListOf("Journal", "List", "Events")
    Column {

        Box(
            modifier = Modifier
                .offset(offsetX, screenHeight * 0.5f)
                .size(boxWidth, 50.dp)
                .align(Alignment.CenterHorizontally)
                .border(
                    1.dp,
                    Color.Black,
                    shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
                )
                .background(
                    Color(0xFFB98231),
                    shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
                )
                .clip(RoundedCornerShape(20.dp))
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            pagerState.currentPage - 1
                        )
                    }

                },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "backward"
                )
            }

            Text(text = contentState.getOrNull(pagerState.currentPage) ?: "")
            IconButton(
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            pagerState.currentPage + 1
                        )
                    }
                },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "forward"
                )
            }
        }
        // Our page content
        Box(

            modifier = Modifier
                .offset(offsetX, screenHeight * 0.5f)
                .size(boxWidth, 150.dp)
                .border(1.dp, Color.Black)
                .background(color = Color(0xFFE8D5BA))


        ) {

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
//                Text(text = "${journal.content}", maxLines = 3)

                when (page) {
                    0 -> JournalScreen()
                    1 -> ListScreen()
                    2 -> EventScreen()
                    else -> {
                        ListScreen()
                    }
                }
            }


        }

    }
}

@Composable
fun ListScreen(viewModel: ListsViewModel = hiltViewModel()) {
    val listsState = viewModel.listFlow.collectAsState(initial = emptyList()).value ?: emptyList()
    val isLoading = viewModel.isLoading.collectAsState(initial = true).value

    Column {
        LazyColumn (contentPadding = PaddingValues(top = 8.dp, start = 20.dp)){
            item {
                if (isLoading) {
                    LinearProgressIndicator(
                        color = Color(0xFF646EF5),
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                    )
                } else if (listsState.isEmpty()) {
                    Text(
                        text = "No Lists Found",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
            items(listsState) { lists ->
                Text(text = "List: ${lists.name}")
                Text(text = "-=================================-")
            }


        }
    }
}

@Composable
fun JournalScreen(viewModel: JournalEntryViewModel = hiltViewModel()) {

    val entriesState = viewModel.entryList.collectAsState(initial = emptyList()).value ?: emptyList()
    val isLoading = viewModel.isLoading.collectAsState(initial = true).value
    Column {
        LazyColumn(contentPadding = PaddingValues(top = 8.dp, start = 20.dp)) {
            item {
                if (isLoading) {
                    LinearProgressIndicator(
                        color = Color(0xFF646EF5),
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                    )
                } else if (entriesState.isEmpty()) {
                    Text(
                        text = "No Journals Found",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
                else {
                    // Display the first entry only if it hasn't been displayed before
                    Text(text = "${entriesState.first().created_at.date}",fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Journal: ${entriesState.first().content}", maxLines = 4)

                }
            }



        }
    }


}
@Composable
fun EventScreen(viewModel: EventViewModel = hiltViewModel()) {
    val eventState = viewModel.eventList.collectAsState(initial = emptyList()).value ?: emptyList()
    val isLoading = viewModel.isLoading.collectAsState(initial = true).value
    Column {
        LazyColumn (contentPadding = PaddingValues(top = 8.dp, start = 20.dp)){
            item {
                if (isLoading) {
                    LinearProgressIndicator(
                        color = Color(0xFF646EF5),
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                    )
                } else if (eventState.isEmpty()) {
                    Text(
                        text = "No Events Found",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
            items(eventState) { events ->
                Text(text = "${events.date_time.date}",fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = "Events: ${events.name}")
                Text(text = "-=================================-")
            }


        }
    }
}


