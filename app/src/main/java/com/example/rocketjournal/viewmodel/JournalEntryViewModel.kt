package com.example.rocketjournal.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketjournal.model.DataTransferObjects.JournalEntryDTO
import com.example.rocketjournal.model.Repositories.JournalEntryRepository
import com.example.rocketjournal.model.dataModel.JournalEntryData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class JournalEntryViewModel @Inject constructor(
    private val journalEntryRepository: JournalEntryRepository
): ViewModel() {

    private val _entryList = MutableStateFlow<List<JournalEntryData>?>(listOf())
    val entryList: Flow<List<JournalEntryData>?> = _entryList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading

    private val _content = MutableStateFlow("")
    val content = _content

    init {
        getJournalEntries()
    }


    //ViewModel method that creates a journal entry in database
    fun onCreateJournalEntry() {
        viewModelScope.launch {
            val contentValue = _content.value


        }
    }
    

    fun getJournalEntries() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val entries = journalEntryRepository.getJournalEntries()
                if (entries.isNullOrEmpty()) {
                    Log.d("JournalEntryViewModel", "No entries were fetched.")
                } else {
                    Log.d("JournalEntryViewModel", "Fetched ${entries.size} entries.")
                }
                _entryList.emit(entries?.map { it.asDomainModel() })
            } catch (e: Exception) {
                Log.e("JournalEntryViewModel", "Error fetching entries", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateJournalEntry(journalEntryData: JournalEntryData){
        viewModelScope.launch {
            journalEntryRepository.updateJournalEntry(
                entry_id = journalEntryData.entry_id,
                journal_id = journalEntryData.journal_id,
                created_at = journalEntryData.created_at,
                last_updated = journalEntryData.last_updated,
                content = journalEntryData.content
            )
            getJournalEntries()
        }
    }

    private fun JournalEntryDTO.asDomainModel(): JournalEntryData {
        return JournalEntryData(
            entry_id = this.entry_id ?: -1,
            journal_id = this.journal_id,
            created_at = this.created_at,
            last_updated = this.last_updated,
            content = this.content
        )
    }

}

