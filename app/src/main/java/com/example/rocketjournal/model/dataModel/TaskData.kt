package com.example.rocketjournal.model.dataModel
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

data class TaskData (
    val task_id: Int? = null,
    val list_id: Int,
    val name: String = "",
    val deadline_date: LocalDate? = null, // Maps to SQL's date type
    val deadline_time: LocalTime? = null, // Maps to SQL's time type
    var is_complete: Boolean

)