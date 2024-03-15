package com.example.rocketjournal.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketjournal.model.DataTransferObjects.JournalDTO
import com.example.rocketjournal.model.Repositories.JournalRepository
import com.example.rocketjournal.model.dataModel.JournalData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JournalViewModel @Inject constructor(
    private val journalRepository: JournalRepository
) : ViewModel() {

    private val _journalList = MutableStateFlow<List<JournalData>?>(listOf())
    val journalList: Flow<List<JournalData>?> = _journalList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading

    init {
        getJournals()
    }

    fun getJournals() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val journals = journalRepository.getJournals()
                if (journals.isNullOrEmpty()){
                    Log.d("JournalViewModel", "No journals were fetched")
                }
                else {
                    Log.d("JournalViewModel", "Fetched ${journals.size} journals.")
                }
                _journalList.emit(journals?.map { it.asDomainModel() })
            } catch (e: Exception){
                Log.e("JournalViewModel", "Error fetching lists", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateJournal(journalData: JournalData){
        viewModelScope.launch {
            journalRepository.updateJournal(
                journal_id = journalData.journal_id,
                user_id = journalData.user_id
            )
            getJournals()
        }
    }

    private fun JournalDTO.asDomainModel(): JournalData {
        return JournalData(
            journal_id = this.journal_id ?: -1,
            user_id = this.user_id
        )
    }



}