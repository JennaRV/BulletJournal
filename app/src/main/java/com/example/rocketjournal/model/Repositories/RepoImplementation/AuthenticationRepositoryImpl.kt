package com.example.rocketjournal.model.Repositories.RepoImplementation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.rocketjournal.model.Repositories.AuthenticationRepository
import com.example.rocketjournal.model.dataModel.UserState
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val auth: Auth
) : AuthenticationRepository {

    private val _userState = mutableStateOf<UserState>(UserState.Loading)
    val userState: State<UserState> = _userState

    override suspend fun signIn(email: String, password: String): Boolean {
        return try {
            auth.signInWith(Email){
                this.email = email
                this.password = password
            }
            _userState.value = UserState.Success("Signed up successfully")
            true
        } catch (e: Exception){
            _userState.value = UserState.Error("Error: ${e.message}")
            false
        }
    }

    override suspend fun signUp(email: String, password: String): Boolean {
        return try {
            auth.signUpWith(Email){
                this.email = email
                this.password = password
            }
            true
        } catch (e: Exception) {
            false
        }
    }

}