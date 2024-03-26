package com.example.rocketjournal.view

import LoginButtons
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rocketjournal.model.Repositories.AuthenticationRepository
import com.example.rocketjournal.viewmodel.SignUpViewModel
import com.example.test.CalendarScreen



import com.example.test.WeeklyScreen

import io.ktor.websocket.Frame
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

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

        composable("NewJournal") { NewJournalScreen(navController) }

        // Define other destinations here
        //Brian's Login
        //bramos@ggc.edu
        //123456!
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



