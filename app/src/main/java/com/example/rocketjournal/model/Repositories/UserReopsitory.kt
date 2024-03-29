package com.example.rocketjournal.model.Repositories

import com.example.rocketjournal.model.DataTransferObjects.UserDTO
import com.example.rocketjournal.model.dataModel.UserData

interface UserReopsitory {

    suspend fun createUser(user: UserData): Boolean

    suspend fun getUsers(): List<UserDTO>?

    suspend fun getUser(id: Int): UserDTO

    suspend fun deleteUser(id: Int)

    suspend fun updateUser(
        user_id: Int,
        first_name: String,
        last_name: String,
        username: String,
        email: String,
        password: String,
        theme: String,
        user_auth_id: String,
        journal_id: Int
    )

}