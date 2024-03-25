package com.example.rocketjournal.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketjournal.model.Repositories.AuthenticationRepository
import com.example.rocketjournal.model.Repositories.UserReopsitory
import com.example.rocketjournal.model.dataModel.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val userReopsitory: UserReopsitory,
    private val supabaseClient: SupabaseClient
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: Flow<String> = _email

    private val _password = MutableStateFlow("")
    val password = _password

    val firstName = MutableStateFlow("")
    val lastName = MutableStateFlow("")
    val username = MutableStateFlow("")

    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }


    fun onSignUp() {
        viewModelScope.launch {
            val emailValue = _email.value
            val passwordValue = _password.value
            val firstNameValue = firstName.value
            val lastNameValue = lastName.value
            val usernameValue = username.value

            val result = authenticationRepository.signUp(emailValue, passwordValue)

            if (result) {
                val currentUser = supabaseClient.auth.currentUserOrNull()
                val userAuthId = currentUser?.id ?: throw IllegalStateException("User ID not available")

                val user = UserData(
                   // user_id = 0,
                    first_name = firstNameValue,
                    last_name = lastNameValue,
                    username = usernameValue,
                    email = emailValue,
                    password = passwordValue,
                    theme = "default",
                    user_auth_id = userAuthId
                )

                val createUserResult = userReopsitory.createUser(user)

                if (createUserResult){
                    Log.e("SignUpViewModel","Sign up and create successful")
                }
                else {
                    Log.e("SignUpViewModel", "create user failed")
                }
            } else {
                // Handle sign-up failure
            }
        }
    }
}