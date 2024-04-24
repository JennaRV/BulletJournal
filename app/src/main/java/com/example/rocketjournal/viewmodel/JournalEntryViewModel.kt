package com.example.rocketjournal.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketjournal.model.DataTransferObjects.JournalEntryDTO
import com.example.rocketjournal.model.Repositories.JournalEntryRepository
import com.example.rocketjournal.model.Repositories.RepoImplementation.UserRepositoryImpl
import com.example.rocketjournal.model.dataModel.JournalEntryData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import javax.inject.Inject


@HiltViewModel
class JournalEntryViewModel @Inject constructor(
    private val journalEntryRepository: JournalEntryRepository,
    private val userRepositoryImpl: UserRepositoryImpl
): ViewModel() {

    private val _entryList = MutableStateFlow<List<JournalEntryData>?>(listOf())
    val entryList: Flow<List<JournalEntryData>?> = _entryList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading

    private val _content = MutableStateFlow("")
    val content = _content

    private val _created_at = MutableStateFlow<LocalDateTime?>(null)
    val created_at = _created_at

    init {
        getJournalEntries()
    }

    fun setCreatedAt(created_at: LocalDateTime?) {
        _created_at.value = created_at
        Log.e("JournalEntryViewModel", "$created_at")
    }


    //ViewModel method that creates a journal entry in database
    fun onCreateJournalEntry() {
        viewModelScope.launch {
            val contentValue = _content.value
            val journalID = userRepositoryImpl.getCurrentUserJournalID()?.toInt()

            val journalEntry = JournalEntryData(
                entry_id = 0,
                journal_id = journalID ?: 0,
                created_at = _created_at.value,
                last_updated = _created_at.value,
                content = contentValue
            )

            getJournalEntries()
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

    fun deleteJournalEntry(journalEntryData: JournalEntryData){
        viewModelScope.launch {
            journalEntryRepository.deleteJournalEntry(journalEntryData.entry_id)
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

