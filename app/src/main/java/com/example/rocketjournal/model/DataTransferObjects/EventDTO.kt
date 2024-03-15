package com.example.rocketjournal.model.DataTransferObjects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate
import kotlinx.datetime.LocalDateTime
import java.time.ZonedDateTime

@Serializable
data class EventDTO(
    @SerialName("event_id")
    var event_id: Int? = null,

    @SerialName("user_id")
    var user_id: Int,

    @SerialName("name")
    var name: String,

    @SerialName("date_time")
    var date_time: LocalDateTime,

    @SerialName("details")
    var details: String


)