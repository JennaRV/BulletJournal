package com.example.rocketjournal.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rocketjournal.view.Journal.JournalEntry
import com.example.rocketjournal.view.Journal.JournalMainDash
import com.example.rocketjournal.view.Journal.NewJournalScreen
import com.example.test.CalendarScreen
import com.example.test.WeeklyScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(navController: NavHostController){



    NavHost(navController = navController, startDestination = "login") {

        // Main Navigation
        composable("home") { MainDashboard(navController) }
        composable("calendar") { CalendarScreen(navController)  }
        composable("list") { ListsScreen(navController) }
        composable("journal") { JournalMainDash(navController) }
        composable("habit") {  }
        // Setting Navigation
        composable("settings") {  }
        composable("profile") {  }
        // Login Navigation
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignUp(navController) }
        composable("loginPage"){LoginPage(navController)}
        // Other Navigation
        composable("weekly") { WeeklyScreen(navController) }
        composable("daily/{date}") {backStackEntry -> DailyScreen(navController, backStackEntry.arguments?.getString("date"))}


        // Define other destinations here

        //newJournal

        composable("newJournal") { NewJournalScreen(navController) }
        composable("journalEntry") { JournalEntry(navController) }



    }
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    //List for the location and assigning image for icon
    //TODO: find images to put in the res/drawable folder implement it,
    //  Icons.Filled.Home is a replacement for now.
    val actions = listOf(
        "journal" to Icons.Filled.Home,
        "list" to Icons.Filled.Home,
        "home" to Icons.Filled.Home,
        "calendar" to Icons.Filled.Home,
        "habit" to Icons.Filled.Home
    )
    Column {

        Spacer(modifier = Modifier.weight(0.1f))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF8B93FC))
                .border(1.dp, Color.Black, shape = RectangleShape)
                .height(60.dp)

        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                actions.forEach { (location, icon) ->
                    CircleIcon {
                        ElevatedButtonWithIcon(
                            icon = icon,
                            navController = navController,
                            location = location
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun ElevatedButtonWithIcon(icon: ImageVector, navController: NavController, location: String) {
    ElevatedButton(
        onClick = { navController.navigate(location) },
        modifier = Modifier.size(50.dp),
        shape = CircleShape,
        colors = ButtonDefaults.elevatedButtonColors(
            Color(0xFFBCC0FF)
        )
    ) {
        Icon(icon, contentDescription = location)
    }
}
@Composable
fun CircleIcon(content: @Composable () -> Unit){
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .border(1.dp, Color.Black, CircleShape)
    ){
        content()
    }
}



