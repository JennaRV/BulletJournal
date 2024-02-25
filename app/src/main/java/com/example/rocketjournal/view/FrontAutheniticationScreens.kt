package com.example.rocketjournal.view

import AppBackgroundFront
import LoginButtons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.rocketjournal.viewmodel.SupabaseAuthViewModel


//The Launcher page when user opens the app
@Composable
fun LoginScreen(navController: NavController) {
    AppBackgroundFront {
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

    AppBackgroundFront {
        SignUpForm(navController)

    }
}

@Composable
fun LoginPage(
    navController: NavController,
    viewModel: SupabaseAuthViewModel = viewModel()
) {
    val context = LocalContext.current
    val userState by viewModel.userState

    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var currentUserState by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.isUserLoggedIn(context)
    }

    AppBackgroundFront {
        LogInForm(navController)

    }
}