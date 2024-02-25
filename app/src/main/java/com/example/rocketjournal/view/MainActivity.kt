package com.example.rocketjournal.view

import AppBackground
import LoginButtons
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.example.rocketjournal.viewmodel.SupabaseAuthViewModel
import io.github.jan.supabase.SupabaseClient
//import io.supabase.client.SupabaseClient.Companion.create
//import io.supabase.client.PostgrestClient
//import io.supabase.client.SupabaseClient
//import io.supabase.client.SupabaseClient.Companion.create

import io.github.jan.supabase.gotrue.*



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


    //The Launcher page when user opens the app
    @Composable
    fun LoginScreen(navController: NavController) {
        AppBackground {
            LoginButtons(navController)
        }
    }

    //Where the user will sign up for
    @Composable
    fun SignUp(
        navController: NavController,
        viewModel: SupabaseAuthViewModel = viewModel()
    ){
        val context = LocalContext.current
        val userState by viewModel.userState

        var userEmail by remember { mutableStateOf("") }
        var userPassword by remember { mutableStateOf("") }
        var currentUserState by remember { mutableStateOf("") }

        LaunchedEffect(Unit){
            viewModel.isUserLoggedIn(context)
        }

        AppBackground {
            SignUpForm(navController)

        }
    }
}
