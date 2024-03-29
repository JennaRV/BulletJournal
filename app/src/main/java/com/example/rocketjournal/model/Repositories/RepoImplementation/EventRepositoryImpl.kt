package com.example.rocketjournal.model.Repositories.RepoImplementation

import com.example.rocketjournal.model.DataTransferObjects.EventDTO
import com.example.rocketjournal.model.Repositories.EventRepository
import com.example.rocketjournal.model.dataModel.EventData
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import kotlinx.datetime.LocalDateTime
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
    private val storage: Storage,
    private val userRepositoryImpl: UserRepositoryImpl
) : EventRepository {

    override suspend fun createEvent(event: EventData): Boolean {
        return try {
            withContext(Dispatchers.IO){
                val eventDTO = EventDTO(
                    event_id = event.event_id,
                    user_id = event.user_id,
                    name = event.name,
                    date_time = event.date_time,
                    details = event.details
                )
                postgrest.from("event").insert(eventDTO)
                true
            }
            true
        } catch (e: Exception){
            throw e
        }
    }

    override suspend fun getEvents(): List<EventDTO>? {
        return withContext(Dispatchers.IO) {
            val result = postgrest.from("event")
                .select(){
                    filter {
                        userRepositoryImpl.getCurrentUserID()?.let { eq("user_id", it) }
                    }
                }.decodeList<EventDTO>()
            result
        }
    }

    override suspend fun getEvent(id: Int): EventDTO {
        return withContext(Dispatchers.IO){
            postgrest.from("event").select() {
                filter {
                    eq("event_id", id)
                }
            }.decodeSingle<EventDTO>()
        }
    }

    override suspend fun deleteEvent(id: Int) {
        return withContext(Dispatchers.IO){
            postgrest.from("event").delete {
                filter {
                    eq("event_id", id)
                }
            }.decodeSingle<EventDTO>()
        }
    }

    override suspend fun updateEvent(
        event_id: Int,
        user_id: Int,
        name: String,
        date_time: LocalDateTime,
        details: String
    ) {
        withContext(Dispatchers.IO) {
            postgrest.from("event").update({
                set("name", name)
                set("date_time", date_time)
                set("details", details)
            }) {
                filter {
                    eq("event_id", event_id)
                }
            }
        }
    }

}