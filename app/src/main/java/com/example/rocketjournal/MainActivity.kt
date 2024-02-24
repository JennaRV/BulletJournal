package com.example.rocketjournal


import AppBackgroundFront
import AppBackgroundGeneral
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
import com.example.rocketjournal.view.Navigation

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RocketJournalTheme {
                val navController = rememberNavController()
                Navigation(navController = navController)
            }
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
}
