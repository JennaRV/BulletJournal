package com.example.rocketjournal.view

import AppBackgroundFront
import AppBackgroundGeneral
import LoginButtons
import com.example.rocketjournal.MainActivity
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.ktor.websocket.Frame

@Composable
fun Navigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = "home") {

        composable("home") { MainDashboard(navController) }
        composable("calendar") {  }
        composable("list") {  }
        composable("journal") {  }
        composable("habit") {  }
        composable("settings") {  }
        composable("profile") {  }
        composable("login") { LoginScreen(navController) }
        composable("signup") {  }
        // Define other destinations here
    }
}
@Composable
fun LoginScreen(navController: NavController) {
    AppBackgroundFront {
        LoginButtons(navController)
    }
}

@Composable
fun SignUp(navController: NavController){
    AppBackgroundGeneral {

    }
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    Row (){
        Button(onClick = {}) {
            Row {
                Text(text = "Journals")
            }
        }
        Button(onClick = {}) {
            Row {
                Text(text = "List")
            }
        }
        Button(onClick = { navController.navigate("home")}) {
            Row {
                Text(text = "Home")
            }
        }
        Button(onClick = {navController.navigate("login")}) {
            Row {
                Text(text = "Login")
            }
        }


    }
}

