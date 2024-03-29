package com.example.rocketjournal.model.DataTransferObjects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JournalDTO (

    @SerialName("journal_id")
    val journal_id: Int? = null,

    @SerialName("user_id")
    val user_id: Int

)