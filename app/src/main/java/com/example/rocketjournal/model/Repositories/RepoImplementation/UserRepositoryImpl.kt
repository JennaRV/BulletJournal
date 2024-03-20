package com.example.rocketjournal.model.Repositories.RepoImplementation

import com.example.rocketjournal.model.DataTransferObjects.UserDTO
import com.example.rocketjournal.model.Repositories.UserReopsitory
import com.example.rocketjournal.model.dataModel.UserData
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
    private val storage: Storage
) : UserReopsitory {

    override suspend fun createUser(user: UserData): Boolean {
        return try {
            withContext(Dispatchers.IO){
                val userDTO = UserDTO(
                    //user_id = user.user_id,
                    first_name = user.first_name,
                    last_name = user.last_name,
                    username = user.username,
                    email = user.email,
                    password = user.password,
                    theme = user.theme,
                    user_auth_id = user.user_auth_id
                )
                postgrest.from("user").insert(userDTO)
                true
            }
            true
        } catch (e: Exception){
            throw e
        }
    }

    override suspend fun getUsers(): List<UserDTO>? {
        return withContext(Dispatchers.IO) {
            val result = postgrest.from("user")
                .select().decodeList<UserDTO>()
            result
        }
    }

    override suspend fun getUser(id: Int): UserDTO {
        return withContext(Dispatchers.IO){
            postgrest.from("user").select() {
                filter {
                    eq("user_id", id)
                }
            }.decodeSingle<UserDTO>()
        }
    }

    override suspend fun deleteUser(id: Int) {
        return withContext(Dispatchers.IO){
            postgrest.from("user").delete {
                filter {
                    eq("user_id", id)
                }
            }.decodeSingle<UserDTO>()
        }
    }

    override suspend fun updateUser(
        user_id: Int,
        first_name: String,
        last_name: String,
        username: String,
        email: String,
        password: String,
        theme: String,
        user_auth_id: String
    ) {
        TODO("Not yet implemented")
    }
}