package com.example.rocketjournal.model.Repositories.RepoImplementation

import com.example.rocketjournal.model.DataTransferObjects.JournalEntryDTO
import com.example.rocketjournal.model.Repositories.JournalEntryRepository
import com.example.rocketjournal.model.dataModel.JournalEntryData
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDateTime
import javax.inject.Inject

class JournalEntryRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
    private val storage: Storage
) : JournalEntryRepository{

    override suspend fun createJournalEntry(journalEntry: JournalEntryData): Boolean {
        return try {
            withContext(Dispatchers.IO) {

                val journalEntryDTO = JournalEntryDTO(
                    entry_id = journalEntry.entry_id,
                    journal_id = journalEntry.journal_id,
                    created_at = journalEntry.created_at,
                    last_updated = journalEntry.last_updated,
                    content = journalEntry.content
                )
                postgrest.from("journal_entry").insert(JournalEntryDTO)
                true
            }
            true
        } catch (e: Exception){
            throw e
        }
    }

    override suspend fun getJournalEntries(): List<JournalEntryDTO>? {
        return withContext(Dispatchers.IO) {
            val result = postgrest.from("journal_entries")
                .select().decodeList<JournalEntryDTO>()
            result
        }
    }

    override suspend fun getJournalEntry(id: Int): JournalEntryDTO {
        return withContext(Dispatchers.IO){
            postgrest.from("journal_entries").select() {
                filter {
                    eq("journal_id", id)
                }
            }.decodeSingle<JournalEntryDTO>()
        }
    }

    override suspend fun deleteJournalEntry(id: Int) {
        return withContext(Dispatchers.IO){
            postgrest.from("journal_entries").delete{
                filter {
                    eq("journal_entries", id)
                }
            }.decodeSingle<JournalEntryDTO>()
        }
    }

    override suspend fun updateJournalEntry(
        entry_id: Int,
        journal_id: Int,
        created_at: LocalDateTime,
        last_updated: LocalDateTime,
        content: String
    ) {
        withContext(Dispatchers.IO){
            postgrest.from("journal_entries").update({
                //add any columns that would be updated here
                set("last_updated", last_updated)
                set("content", content)
            }) {
                filter {
                    eq("entry_id", entry_id)
                }
            }

        }
    }

}