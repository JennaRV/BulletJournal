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
import io.github.jan.supabase.SupabaseClient
//import io.supabase.client.SupabaseClient.Companion.create
//import io.supabase.client.PostgrestClient
//import io.supabase.client.SupabaseClient
//import io.supabase.client.SupabaseClient.Companion.create


val supabase = createSupabaseClient(
    supabaseUrl = "https://mwkkgjpthaidmfbrwcse.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im13a2tnanB0aGFpZG1mYnJ3Y3NlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDY1NTA0MTQsImV4cCI6MjAyMjEyNjQxNH0.zlSCONZgUlS2FgNeCdBzvNo_G-2sMxgAhwr9i2fQBI4"
) {
    install(Postgrest)
}



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
            SignUpForm(navController)

        }
    }
}
