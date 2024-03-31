package com.example.rocketjournal.model.Repositories

import com.example.rocketjournal.model.DataTransferObjects.TaskDTO
import com.example.rocketjournal.model.dataModel.TaskData
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

interface TaskRepository {
    suspend fun createTask(task: TaskData): Boolean

    suspend fun getTasks(listID: Int): List<TaskDTO>?

    suspend fun getTask(id: Int): TaskDTO?

    suspend fun deleteTask(id: Int)

    suspend fun updateTask(
        task_id: Int? = null,
        list_id: Int,
        name: String = "",
        deadline_date: LocalDate? = null, // Maps to SQL's date type
        deadline_time: LocalTime? = null, // Maps to SQL's time type
        is_complete: Boolean
    )

}