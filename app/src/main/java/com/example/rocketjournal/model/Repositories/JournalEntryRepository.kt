package com.example.rocketjournal.model.Repositories

import com.example.rocketjournal.model.DataTransferObjects.JournalEntryDTO
import com.example.rocketjournal.model.dataModel.JournalEntryData
import kotlinx.datetime.LocalDateTime

interface JournalEntryRepository {

    suspend fun createJournalEntry(journalEntry: JournalEntryData): Boolean

    suspend fun getJournalEntries(): List<JournalEntryDTO>?

    suspend fun getJournalEntry(id: Int): JournalEntryDTO

    suspend fun deleteJournalEntry(id: Int)

    suspend fun updateJournalEntry(
        entry_id: Int,
        journal_id: Int,
        created_at: LocalDateTime,
        last_updated: LocalDateTime,
        content: String
    )

}