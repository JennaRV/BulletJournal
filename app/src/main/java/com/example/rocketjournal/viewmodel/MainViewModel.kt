package com.example.rocketjournal.viewmodel

// MainViewModel.kt
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

class MainViewModel : ViewModel() {
    val loginDialogState = mutableStateOf(false)
    val signUpDialogState = mutableStateOf(false)

    fun onLoginClick() {
        loginDialogState.value = true
    }

    fun onSignUpClick() {
        signUpDialogState.value = true
    }

    fun dismissDialog() {
        loginDialogState.value = false
        signUpDialogState.value = false
    }
}