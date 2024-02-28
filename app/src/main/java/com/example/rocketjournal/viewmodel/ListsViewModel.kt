package com.example.rocketjournal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketjournal.model.dataModel.ListData
import com.example.rocketjournal.model.network.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ListsViewModel: ViewModel() {
    val lists = MutableStateFlow<List<ListData>>(emptyList())
fun getLists() {
    viewModelScope.launch {
        try {
            val currentUserId = 1 // Replace with the actual current user ID

            val listsData = SupabaseClient.client.from("task_list").select(columns = Columns.list("name")) {
                filter {
                    eq("user_id", currentUserId)
                }
            }
        } catch (e: Exception){

        }
    }
}


}