package com.example.rocketjournal.model.network

import com.example.rocketjournal.model.dataModel.ListData
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.result.PostgrestResult

class ListsRepository {

    suspend fun fetchLists(): Result<Unit> {
        return runCatching {
            val response = SupabaseClient.client
                .from("task_list") // Assuming "task_list" is your table name
                .select(Columns.ALL)
                .run {  }

            // Assuming you have a way to parse the response body into a List<ListData>
            parseResponseBody(response) // This is a placeholder for however you parse your response
        }
    }

    private fun parseResponseBody(response: Unit) {

    }


}
