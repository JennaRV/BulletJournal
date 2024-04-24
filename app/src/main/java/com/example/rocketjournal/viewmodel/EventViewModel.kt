package com.example.rocketjournal.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketjournal.model.DataTransferObjects.EventDTO
import com.example.rocketjournal.model.Repositories.EventRepository
import com.example.rocketjournal.model.Repositories.RepoImplementation.UserRepositoryImpl
import com.example.rocketjournal.model.dataModel.EventData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import javax.inject.Inject


@HiltViewModel
class EventViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val userRepositoryImpl: UserRepositoryImpl
): ViewModel() {

    private val _eventList = MutableStateFlow<List<EventData>?>(listOf())
    val eventList: Flow<List<EventData>?> = _eventList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading

    //Added here
    private val _name = MutableStateFlow("")
    val name = _name
//    private val _date_time = MutableStateFlow<LocalDateTime>(LocalDateTime.now())
//    val date_time = _date_time
    private val _date_time = MutableStateFlow<LocalDateTime?>(null)
    val date_time = _date_time

    private val _details = MutableStateFlow("")
    val details = _details
    // finish adding here

    init{
        getEvents()
    }

    //Added here
    fun setDateTime(date_time: LocalDateTime) {
        _date_time.value = date_time // Update the StateFlow value
        Log.e("EVM", "$date_time")
    }
    // finish adding here

    //Added here
    fun onEventCreation() {
        viewModelScope.launch {
            val nameValue = _name.value
            val dateTimeValue = _date_time.value
            val eventID = 23
            val detailsValue = _details.value
            val getUserID = userRepositoryImpl.getCurrentUserID()
            val userID: Int = if (getUserID != null) getUserID else 0

            Log.e("CREATEEVENT", "$nameValue, $dateTimeValue")

            val event = dateTimeValue?.let {
                EventData(
                    eventID,
                    userID,
                    nameValue,
                    it,
                    detailsValue

                )
            }

            val result = event?.let { eventRepository.createEvent(it) }

            if (result == true) {
                Log.e("EventViewModel", "create event was successful")
                getEvents()
            } else { //if not it log that the create user was failed
                Log.e("EventViewModel", "create event failed")
            }
        }
    }
    //finished adding here

    fun getEvents() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val events = eventRepository.getEvents()
                if (events.isNullOrEmpty()) {
                    Log.d("EventViewModel", "No events were fetched.")
                }
                else {
                    Log.d("EventViewModel", "Fetched ${events.size} events.")
                }
                _eventList.emit(events?.map { it.asDomainModel() })
            } catch (e: Exception) {
                Log.e("EventViewModel", "Error fetching events", e)
            } finally {
                _isLoading.value = false
            }
        }
    }



    fun updateEvent(eventData: EventData){
        viewModelScope.launch {
            eventRepository.updateEvent(
                event_id = eventData.event_id,
                user_id = eventData.user_id,
                name = eventData.name,
                date_time = eventData.date_time,
                details = eventData.details
            )
        }
    }

    private fun EventDTO.asDomainModel(): EventData {
        return EventData(
            event_id = this.event_id ?: -1,
            user_id = this.user_id,
            name = this.name,
            date_time = this.date_time,
            details = this.details
        )
    }
}