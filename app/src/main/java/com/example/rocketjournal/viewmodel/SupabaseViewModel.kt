package com.example.rocketjournal.viewmodel
import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketjournal.model.dataModel.UserData
import com.example.rocketjournal.model.dataModel.UserState
import com.example.rocketjournal.model.network.SupabaseClient
import com.example.rocketjournal.utils.SharedPreferanceHelper
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch
class SupabaseViewModel: ViewModel() {

    private val _userState = mutableStateOf<UserState>(UserState.Loading)
    val userState: State<UserState> = _userState
    fun saveUser(first_name: String, last_name: String, username: String, email: String, password: String){
        viewModelScope.launch {
            try{
                _userState.value = UserState.Loading
                SupabaseClient.client.postgrest["User"].insert(
                    UserData(first_name, last_name, username, email, password)
                )
            } catch(e: Exception){
                _userState.value = UserState.Error("Error: ${e.message}")
            }
        }
    }



}