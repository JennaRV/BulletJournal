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
    private val storage: Storage,
    private val userRepositoryImpl: UserRepositoryImpl
) : JournalEntryRepository{

    override suspend fun createJournalEntry(journalEntry: JournalEntryData): Boolean {
        return try {
            withContext(Dispatchers.IO) {

                val journalEntryDTO = JournalEntryDTO(

                    journal_id = journalEntry.journal_id,
                    created_at = journalEntry.created_at,
                    last_updated = journalEntry.last_updated,
                    content = journalEntry.content
                )
                postgrest.from("journal_entry").insert(journalEntryDTO)
                true
            }
            true
        } catch (e: Exception){
            throw e
        }
    }

    override suspend fun getJournalEntries(): List<JournalEntryDTO>? {

        //val currentUserJournalID = userRepositoryImpl.getCurrentUserJournalID()?.toInt()

        return withContext(Dispatchers.IO) {
            val result = postgrest.from("journal_entry")
                .select{
                    filter {
                        userRepositoryImpl.getCurrentUserJournalID()?.let { eq("journal_id", it)}
                    }
                }
                .decodeList<JournalEntryDTO>()
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
        created_at: LocalDateTime?,
        last_updated: LocalDateTime?,
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

//    suspend fun getCurrentUserJournalID(): Int? {
//        val currentUserId = userRepositoryImpl.getCurrentUserID()
//
//        return withContext(Dispatchers.IO) {
//            //this is attempting to find the user with that user UID (user_auth_id) and retrieving the userDTO associated with it.
//            val userIDQueryResult = postgrest.from("user")
//                .select() {
//                    filter {
//                        if (currentUserId != null) {
//                            eq("user_id", currentUserId)
//                        }
//                    }
//                }.decodeSingle<UserDTO>().journal_id
//            userIDQueryResult
//        }
//
//    }

}