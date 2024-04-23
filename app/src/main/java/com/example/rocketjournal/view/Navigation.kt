package com.example.rocketjournal.view

import LoginButtons

import android.annotation.SuppressLint

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home

import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon

import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState

import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection


import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.rocketjournal.model.Repositories.AuthenticationRepository
import com.example.rocketjournal.viewmodel.SignInViewModel
import com.example.rocketjournal.viewmodel.SignOutViewModel
import com.example.rocketjournal.viewmodel.SignUpViewModel

import com.example.rocketjournal.view.Journal.JournalEntry
import com.example.rocketjournal.view.Journal.JournalMainDash
import com.example.rocketjournal.view.Journal.NewJournalScreen

import com.example.test.CalendarScreen
import com.example.test.EventCreation
import com.example.test.MainEvent
import com.example.test.WeeklyScreen
import io.github.jan.supabase.SupabaseClient


import io.ktor.websocket.Frame
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(navController: NavHostController) {
    // should use the signOutViewModel instances.
    val signOutViewModel: SignOutViewModel = viewModel()
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = navBackStackEntry?.destination?.route
    var showBottomBar by rememberSaveable { mutableStateOf(true) }
    val coroutine = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }
    showBottomBar = when (currentRoute) {
        // on this screen bottom bar should be hidden
        "login" -> false
        "signup" -> false
        "loginPage" -> false
        else -> true // in all other cases show bottom bar
    }
    ModalNavigationDrawer( // Shows the drawer on the left
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(280.dp), drawerContainerColor = Color(0xFF646EF5)) {
                Box(
                    modifier = Modifier
                        .background(Color(red = 214, green = 66, blue = 105))
                        .height(150.dp)
                        .fillMaxWidth()
                )
                HorizontalDivider()
                NavigationDrawerItem(colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color(0xFF646EF5),
                    unselectedTextColor = Color(0xFFFFFFFF),
                    unselectedIconColor = Color(0xFFFFFFFF)),
                    label = { Text(text = "Profile") },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "profile"
                        )
                    },
                    onClick = {
                        coroutine.launch {
                            drawerState.close()
                        }
                        navController.navigate("profile")
                    })
                NavigationDrawerItem(colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color(0xFF646EF5),
                    unselectedTextColor = Color(0xFFFFFFFF),
                    unselectedIconColor = Color(0xFFFFFFFF)),
                    label = { Text(text = "Settings") },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "setting"
                        )
                    },
                    onClick = {
                        coroutine.launch {
                            drawerState.close()
                        }

                    })
                NavigationDrawerItem(colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color(0xFF646EF5),
                    unselectedTextColor = Color(0xFFFFFFFF),
                    unselectedIconColor = Color(0xFFFFFFFF)),
                    label = { Text(text = "Logout") },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Logout"
                        )
                    },
                    onClick = {
                        coroutine.launch {
                            drawerState.close()
                        }
                        signOutViewModel.signOut()
                        navController.navigate("login")


                    })
            }
        },
        drawerState = drawerState,
        gesturesEnabled = true
    ) {
        Scaffold(
            bottomBar = {
                if (showBottomBar) {
                    BottomAppBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(65.dp)
                            .border(1.dp, Color.Black),
                        containerColor = Color(0xFF8FA1F8)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = {
                                selected.value = Icons.Default.Home
                                navController.navigate("journal")
                            }) {
                                Icon(
                                    imageVector = Icons.Outlined.Create,
                                    contentDescription = "journal"
                                )

                            }
                            IconButton(onClick = {
                                selected.value = Icons.Default.Home
                                navController.navigate("list")
                            }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Outlined.List,
                                    contentDescription = "list"
                                )

                            }
                            IconButton(onClick = {
                                selected.value = Icons.Default.Home
                                navController.navigate("home")
                            }) {
                                Icon(
                                    imageVector = Icons.Outlined.Home,
                                    contentDescription = "home"
                                )

                            }
                            IconButton(onClick = {
                                selected.value = Icons.Default.Home
                                navController.navigate("calendar")
                            }) {
                                Icon(
                                    imageVector = Icons.Outlined.DateRange,
                                    contentDescription = "calendar"
                                )

                            }
                            IconButton(onClick = {
                                selected.value = Icons.Default.Home
                                navController.navigate("event")
                            }) {
                                Icon(
                                    imageVector = Icons.Outlined.Star,
                                    contentDescription = "event"
                                )
                            }
                            IconButton(onClick = { coroutine.launch { drawerState.open() }}) {
                                Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "menu")
                            }
                        }
                    }
                }
            }
        )
        {
            NavHost(navController = navController, startDestination = "login") {

                // Main Navigation
                composable("home") { MainDashboard(navController) }
                composable("calendar") { CalendarScreen(navController) }
                composable("list") { ListsScreen(navController) }
                composable("journal") { JournalMainDash(navController) }
                composable("event") { MainEvent(navController) }
                // Setting Navigation
                composable("settings") { }
                composable("profile") { ProfileUI(navController) }
                // Login Navigation
                composable("login") { LoginScreen(navController) }
                composable("signup") { SignUp(navController) }
                composable("loginPage") { LoginPage(navController) }
                // Other Navigation
                composable("weekly") { WeeklyScreen(navController) }
                composable("daily/{date}") { backStackEntry ->
                    DailyScreen(
                        navController,
                        backStackEntry.arguments?.getString("date")
                    )
                }
                composable("task_list/{listId}") { backStackEntry ->
                    // Extract the listId from the backStackEntry
                    val listId = backStackEntry.arguments?.getString("listId")?.toIntOrNull()

                    // Pass the listId to your TaskList composable
                    if (listId != null) {
                        TaskList(navController, listId)
                    }
                }
                //newJournal
                composable("newJournal") { NewJournalScreen(navController) }
                composable("journalEntry") { JournalEntry(navController) }

                composable("newEvent") { EventCreation(navController) }
            }
        }
    }
}