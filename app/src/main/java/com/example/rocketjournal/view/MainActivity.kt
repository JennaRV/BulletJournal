package com.example.rocketjournal.view

import AppBackgroundFront
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
//import com.example.rocketjournal.viewmodel.SupabaseAuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.github.jan.supabase.SupabaseClient
//import io.supabase.client.SupabaseClient.Companion.create
//import io.supabase.client.PostgrestClient
//import io.supabase.client.SupabaseClient
//import io.supabase.client.SupabaseClient.Companion.create

import io.github.jan.supabase.gotrue.*


@AndroidEntryPoint
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
}
