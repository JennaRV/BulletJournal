package com.example.rocketjournal.model.Repositories

import com.example.rocketjournal.model.DataTransferObjects.EventDTO
import com.example.rocketjournal.model.dataModel.EventData
import kotlinx.datetime.LocalDateTime

interface EventRepository {

    suspend fun createEvent(event: EventData): Boolean

    suspend fun getEvents(): List<EventDTO>?

    suspend fun getEvent(id: Int): EventDTO

    suspend fun deleteEvent(id: Int)

    suspend fun updateEvent(
        event_id: Int,
        user_id: Int,
        name: String,
        date_time: LocalDateTime,
        details: String
    )
}