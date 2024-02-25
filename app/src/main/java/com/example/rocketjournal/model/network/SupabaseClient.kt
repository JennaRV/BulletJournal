package com.example.rocketjournal.model.network

import io.github.jan.supabase.createSupabaseClient

import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.gotrue.Auth

object SupabaseClient {
    val client = createSupabaseClient(
        supabaseUrl = "https://mwkkgjpthaidmfbrwcse.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im13a2tnanB0aGFpZG1mYnJ3Y3NlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDY1NTA0MTQsImV4cCI6MjAyMjEyNjQxNH0.zlSCONZgUlS2FgNeCdBzvNo_G-2sMxgAhwr9i2fQBI4"
    ) {
        install(Auth)
        install(Postgrest)
    }
}