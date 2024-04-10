package com.example.rocketjournal.model.DataTransferObjects

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TaskDTO (

    @SerialName("task_id")
    val task_id: Int? = null,

    @SerialName("list_id")
    val list_id: Int,

    @SerialName("name")
    val name: String = "",

    @SerialName("deadline_date")
    val deadline_date: LocalDate? = null,

    @SerialName("deadline_time")
    val deadline_time: LocalTime? = null,

    @SerialName("is_complete")
    val is_complete: Boolean

)