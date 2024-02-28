package com.example.rocketjournal.view

import LoginButtons

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
import com.example.rocketjournal.viewmodel.ListsViewModel
import io.ktor.websocket.Frame

@Composable
fun Navigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = "list") {

        composable("home") { MainDashboard(navController) }
        composable("calendar") {  }
        composable("list") { ListsScreen(navController, viewModel = ListsViewModel()) }
        composable("journal") {  }
        composable("habit") {  }
        composable("settings") {  }
        composable("profile") {  }
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignUp(navController) }
        composable("loginPage"){LoginPage(navController)}
        // Define other destinations here
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
        Button(onClick = {navController.navigate("list")}) {
            Row {
                Text(text = "List")
            }
        }
        Button(onClick = { navController.navigate("home")}) {
            Row {
                Text(text = "Home")
            }
        }
        Button(onClick = { navController.navigate("tasks")}) {
            Row {
                Text(text = "Tasks")
            }
        }


    }
}


