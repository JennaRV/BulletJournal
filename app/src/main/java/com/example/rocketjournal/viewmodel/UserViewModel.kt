package com.example.rocketjournal.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketjournal.model.DataTransferObjects.UserDTO
import com.example.rocketjournal.model.Repositories.UserReopsitory
import com.example.rocketjournal.model.dataModel.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userReopsitory: UserReopsitory
) : ViewModel() {

    private val _userList = MutableStateFlow<List<UserData>?>(listOf())
    val userList: Flow<List<UserData>?> = _userList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val users = userReopsitory.getUsers()
                if (users.isNullOrEmpty()) {
                    Log.d("UserViewModel", "No users were fetched.")
                }
                else {
                    Log.d("ListsViewModel", "Fetched ${users.size} users.")
                }
                _userList.emit(users?.map { it.asDomainModel() })
            } catch (e: Exception) {
                Log.e("ListsViewModel", "Error fetching lists", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

//    fun updateUser(userData: UserData) {
//        viewModelScope.launch {
//            userReopsitory.updateUser(
//                //user_id = userData.user_id,
//                first_name = userData.first_name,
//                last_name = userData.last_name,
//                username = userData.username,
//                email = userData.email,
//                password = userData.password,
//                theme = userData.theme,
//                user_auth_id = userData.user_auth_id
//            )
//            getUsers()
//        }
//    }

    private fun UserDTO.asDomainModel(): UserData {
        return UserData(
            //user_id = this.user_id ?: -1,
            first_name = this.first_name,
            last_name = this.last_name,
            username = this.username,
            email = this.email,
            password = this.password,
            theme = this.theme,
            user_auth_id = this.user_auth_id
        )
    }




}