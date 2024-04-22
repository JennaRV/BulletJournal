package com.example.rocketjournal.model.Repositories.RepoImplementation

import com.example.rocketjournal.model.DataTransferObjects.UserDTO
import com.example.rocketjournal.model.Repositories.UserReopsitory
import com.example.rocketjournal.model.dataModel.UserData
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
    private val storage: Storage,
    private val supabaseClient: SupabaseClient
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
                    user_auth_id = user.user_auth_id,
                    //journal_id = user.journal_id
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
        user_auth_id: String,
        journal_id: Int
    ) {
        TODO("Not yet implemented")
    }

    suspend fun getCurrentUserID(): Int? {
        //this is retrieving the user UID from the authentication process
        val currentUser = supabaseClient.auth.retrieveUserForCurrentSession(updateSession = true)
        val currentUserId = currentUser?.id ?: throw IllegalStateException("User ID not available")

        return withContext(Dispatchers.IO) {
            //this is attempting to find the user with that user UID (user_auth_id) and retrieving the userDTO associated with it.
            val userIDQueryResult = postgrest.from("user")
                .select() {
                    filter {
                        eq("user_auth_id", currentUserId)
                    }
                }.decodeSingle<UserDTO>().user_id
            userIDQueryResult
        }

    }

    //Method to get Current User's Journal ID
    suspend fun getCurrentUserJournalID(): Int? {

        val currentUser = supabaseClient.auth.retrieveUserForCurrentSession(updateSession = true)
        val currentUserId = currentUser?.id ?: throw IllegalStateException("User ID not available")

        val currentUserIdforJournalEntry = getCurrentUserID()?.let { it } ?: throw IllegalStateException("User ID not available")

        return withContext(Dispatchers.IO) {
            //this is attempting to find the user with that user UID (user_auth_id) and retrieving the userDTO associated with it.
            val JournalQueryResult = postgrest.from("user")
                .select() {
                    filter {
                        eq("user_auth_id", currentUserId)
                    }
                }.decodeSingle<UserDTO>().journal_id
            JournalQueryResult
        }
    }

}