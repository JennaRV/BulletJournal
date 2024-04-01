package com.example.rocketjournal.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketjournal.model.DataTransferObjects.TaskDTO
import com.example.rocketjournal.model.Repositories.TaskRepository
import com.example.rocketjournal.model.dataModel.JournalEntryData
import com.example.rocketjournal.model.dataModel.TaskData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _taskList = MutableStateFlow<List<TaskData>?>(listOf())
    val taskList: Flow<List<TaskData>?> = _taskList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading

    private val _name = MutableStateFlow("")
    val name = _name

    fun onCreateTask(listID: Int) {
        val nameValue = name.value.trim() // Trim to remove leading and trailing whitespace
        if (nameValue.isEmpty()) {
            // Handle empty task name error
            return
        }

        viewModelScope.launch {
            val task = TaskData(
                list_id = listID,
                name = nameValue,
                deadline_date = null,
                deadline_time = null,
                is_complete = false
            )

            try {
                val result = taskRepository.createTask(task)
                if (result) {
                    Log.e("TaskViewModel", "create task was successful")
                    getTasks(listID)
                } else {
                    Log.e("TaskViewModel", "create task failed")
                }
            } catch (e: Exception) {
                // Handle exception, e.g., network error
                Log.e("TaskViewModel", "Error creating task", e)
            }

        }
    }


    fun getTasks(listID: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val tasks = taskRepository.getTasks(listID)
                if (tasks.isNullOrEmpty()) {
                    Log.d("TaskViewModel", "No tasks were fetched.")
                } else {
                    Log.d("TaskViewModel", "Fetched ${tasks.size} tasks.")
                }
                _taskList.emit(tasks?.map { it.asDomainModel() })
            } catch (e: Exception) {
                Log.e("TaskViewModel", "Error fetching tasks", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteTask(taskData: TaskData){
        viewModelScope.launch {
            taskData.task_id?.let { taskRepository.deleteTask(it) }
            getTasks(taskData.list_id)
        }
    }

    fun updateTask(taskData: TaskData) {
        viewModelScope.launch {
            taskRepository.updateTask(
                task_id = taskData.task_id,
                list_id = taskData.list_id,
                name = taskData.name,
                deadline_date = taskData.deadline_date,
                deadline_time = taskData.deadline_time,
                is_complete = taskData.is_complete
            )
            getTasks(taskData.list_id)
        }
    }

    private fun TaskDTO.asDomainModel(): TaskData {
        return TaskData(
            task_id = this.task_id ?: -1,
            list_id = this.list_id,
            name = this.name,
            deadline_date = this.deadline_date,
            deadline_time = this.deadline_time,
            is_complete = this.is_complete
        )
    }

}