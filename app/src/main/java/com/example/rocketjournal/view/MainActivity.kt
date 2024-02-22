package com.example.rocketjournal.view

import AppBackground
import LoginButtons
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
//import com.example.rocketjournal.view.LoginButtons
//import com.example.rocketjournal.view.AppBackground
import com.example.rocketjournal.ui.theme.RocketJournalTheme
import com.example.rocketjournal.viewmodel.MainViewModel
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RocketJournalTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") { LoginScreen(navController) }
                    composable("signup") { SignUp(navController) }
                    // Define other destinations here
                }
            }
        }
    }

    @Composable
    fun LoginScreen(navController: NavController) {
        AppBackground {
            LoginButtons(navController)
        }
    }

    @Composable
    fun SignUp(navController: NavController){
        AppBackground {

        }
    }
}
