package com.example.rocketjournal.model.DataTransferObjects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    @SerialName("user_id")
    val user_id: Int? = null,

    @SerialName("first_name")
    var first_name: String,

    @SerialName("last_name")
    var last_name: String,

    @SerialName("username")
    var username : String,

    @SerialName("email")
    var email: String,

    @SerialName("password")
    var password: String,

    @SerialName("theme")
    var theme: String = "default",

    @SerialName("user_auth_id")
    var user_auth_id: String,

    @SerialName("journal_id")
    val journal_id: Int? = null
)