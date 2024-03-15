package com.example.rocketjournal.model.DataTransferObjects

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JournalEntryDTO (

    @SerialName("entry_id")
    val entry_id: Int? = null,

    @SerialName("journal_id")
    val journal_id: Int,

    @SerialName("created_at")
    val created_at: LocalDateTime,

    @SerialName("last_updated")
    val last_updated: LocalDateTime,

    @SerialName("content")
    val content: String


)