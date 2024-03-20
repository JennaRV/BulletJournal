package com.example.rocketjournal.model.dataModel

import kotlinx.serialization.Serializable

@Serializable
data class UserData (
    val user_id: Int? = null,
    val first_name: String = "",
    val last_name: String = "",
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val theme: String = "default",
    val user_auth_id: String
)