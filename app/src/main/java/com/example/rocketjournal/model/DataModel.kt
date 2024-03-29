//package com.example.rocketjournal.model
//import io.github.jan
//import io.supabase.client.PostgrestClient
//import io.supabase.client.SupabaseClient
//import io.supabase.client.SupabaseClient.Companion.create
//
//object DataModel {
//
//    // Define your data classes here
//    data class YourDataModel(...)
//
//    // Repository class to interact with Supabase
//    class SupabaseRepository {
//
//        private val client: SupabaseClient = getClient()
//
//        private fun getClient(): SupabaseClient {
//            return create(
//                supabaseUrl = "https://mwkkgjpthaidmfbrwcse.supabase.co",
//                supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im13a2tnanB0aGFpZG1mYnJ3Y3NlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDY1NTA0MTQsImV4cCI6MjAyMjEyNjQxNH0.zlSCONZgUlS2FgNeCdBzvNo_G-2sMxgAhwr9i2fQBI4"
//            ).apply {
//                install(Postgrest)
//            }
//        }
//
//        // Example function to fetch data from Supabase
//        suspend fun fetchData(): List<YourDataModel> {
//            // Use the Supabase client to interact with your database
//            // For example:
//            val response = client
//                .from("your_table_name")
//                .select("*")
//                .execute()
//
//            if (response.error != null) {
//                // Handle error
//                throw Exception("Error fetching data: ${response.error}")
//            }
//
//            // Process and return data
//            return response.data.map { /* map Supabase response to YourDataModel */ }
//        }
//    }
//}