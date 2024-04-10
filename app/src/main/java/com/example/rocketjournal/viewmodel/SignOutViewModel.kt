package com.example.rocketjournal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketjournal.model.Repositories.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignOutViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel(){


    fun signOut(){
        viewModelScope.launch {
            authenticationRepository.signOut()
        }
    }

}