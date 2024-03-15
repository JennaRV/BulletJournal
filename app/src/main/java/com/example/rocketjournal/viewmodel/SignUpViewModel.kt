package com.example.rocketjournal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketjournal.model.Repositories.AuthenticationRepository
import com.example.rocketjournal.model.Repositories.UserReopsitory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val userReopsitory: UserReopsitory
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: Flow<String> = _email

    private val _password = MutableStateFlow("")
    val password = _password

    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }

    fun onSignUp() {
        viewModelScope.launch {
            authenticationRepository.signUp(
                email = _email.value,
                password = _password.value
            )
        }
    }
}