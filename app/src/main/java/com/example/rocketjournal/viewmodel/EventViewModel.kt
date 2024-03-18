package com.example.rocketjournal.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketjournal.model.DataTransferObjects.EventDTO
import com.example.rocketjournal.model.Repositories.EventRepository
import com.example.rocketjournal.model.dataModel.EventData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EventViewModel @Inject constructor(
    private val eventRepository: EventRepository
): ViewModel() {

    private val _eventList = MutableStateFlow<List<EventData>?>(listOf())
    val eventList: Flow<List<EventData>?> = _eventList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading

    init{
        getEvents()
    }

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