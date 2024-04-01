package com.example.rocketjournal.model.Repositories

import com.example.rocketjournal.model.DataTransferObjects.JournalDTO
import com.example.rocketjournal.model.dataModel.JournalData

interface JournalRepository {

    suspend fun createJournal(journal: JournalData): Boolean

    suspend fun getJournals(): List<JournalDTO>?

    suspend fun getJournal(id: Int): JournalDTO

    suspend fun deleteJournal(id: Int)

    suspend fun updateJournal(
        journal_id: Int, user_id: Int
    )

}