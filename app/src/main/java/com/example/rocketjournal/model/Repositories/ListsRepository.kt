package com.example.rocketjournal.model.Repositories

import com.example.rocketjournal.model.DataTransferObjects.ListsDTO
import com.example.rocketjournal.model.dataModel.ListData

interface ListsRepository {
    suspend fun createList(list: ListData): Boolean
    suspend fun getLists(): List<ListsDTO>?
    suspend fun getList(id: Int): ListsDTO?
    suspend fun deleteList(id: Int)
    suspend fun updateList(
        list_id: Int, user_id: Int, name: String, is_complete: Boolean
    )
}