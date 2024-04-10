package com.example.rocketjournal.model.Repositories.RepoImplementation

import com.example.rocketjournal.model.DataTransferObjects.ListsDTO
import com.example.rocketjournal.model.DataTransferObjects.TaskDTO
import com.example.rocketjournal.model.Repositories.TaskRepository
import com.example.rocketjournal.model.dataModel.TaskData
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import javax.inject.Inject

class TaskRepositoryImplementation @Inject constructor(
    private val postgrest: Postgrest,
    private val userRepositoryImpl: UserRepositoryImpl
) : TaskRepository {

    override suspend fun createTask(task: TaskData): Boolean {
        return try {
            withContext(Dispatchers.IO){
                val taskDTO = TaskDTO(
                    task_id = task.task_id,
                    list_id = task.list_id,
                    name = task.name,
                    deadline_date = task.deadline_date,
                    deadline_time = task.deadline_time,
                    is_complete = task.is_complete
                )
                postgrest.from("task").insert(taskDTO)
                true
            }
            true
        } catch (e: java.lang.Exception){
            throw e
        }
    }

    override suspend fun getTasks(listID: Int): List<TaskDTO>? {
        return withContext(Dispatchers.IO) {
            //retrieve lists with the current users user_id
            val result = postgrest.from("task")
                .select(){
                    filter {
                        eq("list_id", listID)
                    }
                }
                .decodeList<TaskDTO>()
            result
        }
    }

    override suspend fun getTask(id: Int): TaskDTO? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(id: Int) {
        return withContext(Dispatchers.IO) {
            postgrest.from("task").delete({
                filter {
                    eq("task_id", id)
                }
            })
        }
    }

    override suspend fun updateTask(
        task_id: Int?,
        list_id: Int,
        name: String ,
        deadline_date: LocalDate?, // Maps to SQL's date type
        deadline_time: LocalTime?, // Maps to SQL's time type
        is_complete: Boolean
    ) {
        postgrest.from("task").update({
            set("is_complete", is_complete)
        }) {
            filter {
                if (task_id != null) {
                    eq("task_id", task_id)
                }
            }
        }
    }
}