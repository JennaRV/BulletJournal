package com.example.rocketjournal.model.dataModel

import kotlinx.datetime.LocalDateTime

data class EventData(
    val event_id: Int,
    val user_id: Int,
    val name: String,
    val date_time: LocalDateTime,
    val details: String
)