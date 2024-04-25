package com.example.rocketjournal.model.dataModel

import kotlinx.datetime.LocalDateTime

data class JournalEntryData(
    val entry_id: Int,
    val journal_id: Int,
    val created_at: LocalDateTime?,
    val last_updated: LocalDateTime?,
    val content: String
)

