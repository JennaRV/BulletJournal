package com.example.rocketjournal.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketjournal.model.DataTransferObjects.ListsDTO
import com.example.rocketjournal.model.Repositories.ListsRepository
import com.example.rocketjournal.model.Repositories.RepoImplementation.UserRepositoryImpl
import com.example.rocketjournal.model.dataModel.ListData
import com.example.rocketjournal.model.dataModel.TaskData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ListsViewModel @Inject constructor(
    private val listsRepository: ListsRepository,
    private val userRepositoryImpl: UserRepositoryImpl
) : ViewModel() {

    //this is the list of all to-do lists
    private val _listsFlow = MutableStateFlow<List<ListData>?>(listOf())
    val listFlow: Flow<List<ListData>?> = _listsFlow

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading

    private val _name = MutableStateFlow("")
    val name = _name

    private val _deadline = MutableStateFlow<LocalDateTime?>(null)
    val deadline = _deadline

    init {
        getLists()
    }

    fun setDeadline(deadline: LocalDateTime?) {
        _deadline.value = deadline // Update the StateFlow value
        Log.e("LVM", "$deadline")
    }

    fun onCreateList() {
        viewModelScope.launch {
            val nameValue = _name.value
            val deadlineValue = _deadline.value
            val listID = 23
            val getUserID = userRepositoryImpl.getCurrentUserID()
            val userID: Int = if (getUserID != null) getUserID else 0

            Log.e("CREATELIST", "$nameValue, $deadlineValue")

            val list = ListData(
                listID,
                userID,
                nameValue,
                false,
                deadlineValue

            )

            val result = listsRepository.createList(list)

            if (result) {
                Log.e("ListsViewModel", "create list was successful")
                getLists()
            } else { //if not it log that the create user was failed
                Log.e("ListsViewModel", "create list failed")
            }
        }
    }

    //this is where the listFlow is populated with the lists from the database
    fun getLists() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val lists = listsRepository.getLists()
                if (lists.isNullOrEmpty()) {
                    Log.d("ListsViewModel", "No lists were fetched.")
                } else {
                    Log.d("ListsViewModel", "Fetched ${lists.size} lists.")
                }
                _listsFlow.emit(lists?.map { it.asDomainModel() })
            } catch (e: Exception) {
                Log.e("ListsViewModel", "Error fetching lists", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    suspend fun getList(listID: Int): ListsDTO? {
        return listsRepository.getList(listID)
    }

    fun deleteList(listData: ListData){
        viewModelScope.launch {
            listData.list_id?.let { listsRepository.deleteList(it) }
            getLists()
        }
    }

    fun updateList(listData: ListData) {
        viewModelScope.launch {
            // Update the repository
            listsRepository.updateList(
                list_id = listData.list_id,
                user_id = listData.user_id, // Assuming you need to pass this based on your repository method signature
                name = listData.name,
                is_complete = listData.is_complete,
                deadline = listData.deadline
            )
            // Fetch updated lists again or update local list state as necessary
            getLists()
        }
    }

    private fun ListsDTO.asDomainModel(): ListData {
        return ListData(
            list_id = this.list_id ?: -1,
            user_id = this.user_id,
            name = this.name,
            is_complete = this.is_complete,
            deadline = this.deadline ?: null
        )
    }


        private val _checkedLists = mutableStateMapOf<Int, Boolean>()
        val checkedLists: Map<Int, Boolean> get() = _checkedLists

        fun toggleListCheckedState(listId: Int, isChecked: Boolean) {
            _checkedLists[listId] = isChecked
        }

}

