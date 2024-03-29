package com.example.rocketjournal.model.Repositories.RepoImplementation

import com.example.rocketjournal.model.DataTransferObjects.JournalDTO
import com.example.rocketjournal.model.Repositories.JournalRepository
import com.example.rocketjournal.model.dataModel.JournalData
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JournalRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
    private val storage: Storage,
    private val userRepositoryImpl: UserRepositoryImpl
) : JournalRepository {

    override suspend fun createJournal(journal: JournalData): Boolean {
        return try {
            withContext(Dispatchers.IO){
                val journalDTO = JournalDTO(
                    journal_id = journal.journal_id,
                    user_id = journal.user_id
                )
                postgrest.from("journal").insert(JournalDTO)
                true
            }
            true
        } catch (e: Exception){
            throw e
        }
    }

    override suspend fun getJournals(): List<JournalDTO>? {
        return withContext(Dispatchers.IO) {
            val result = postgrest.from("journal")
                .select(){
                    filter {

                        userRepositoryImpl.getCurrentUserID()?.let { eq("user_id", it) }
                    }
                }.decodeList<JournalDTO>()
            result
        }
    }

    override suspend fun getJournal(id: Int): JournalDTO {
        return withContext(Dispatchers.IO) {
            postgrest.from("journal").select() {
                filter {
                    eq("journal_id", id)
                }
            }.decodeSingle<JournalDTO>()
        }
    }

    override suspend fun deleteJournal(id: Int) {
        return withContext(Dispatchers.IO){
            postgrest.from("journal").delete{
                filter {
                    eq("journal_id", id)
                }
            }
        }
    }


    override suspend fun updateJournal(journal_id: Int, user_id: Int) {
        TODO("Not yet implemented")
    }
}