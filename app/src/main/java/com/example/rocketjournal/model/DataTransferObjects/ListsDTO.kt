package com.example.rocketjournal.model.DataTransferObjects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListsDTO(

    @SerialName("list_id")
    val list_id: Int? = null,

    @SerialName("user_id")
    val user_id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("is_complete")
    var is_complete: Boolean

)
