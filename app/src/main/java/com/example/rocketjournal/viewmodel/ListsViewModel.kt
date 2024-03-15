package com.example.rocketjournal.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rocketjournal.model.DataTransferObjects.ListsDTO
import com.example.rocketjournal.model.Repositories.ListsRepository
import com.example.rocketjournal.model.dataModel.ListData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListsViewModel @Inject constructor(
    private val listsRepository: ListsRepository
) : ViewModel() {
    private val _listsFlow = MutableStateFlow<List<ListData>?>(listOf())
    val listFlow: Flow<List<ListData>?> = _listsFlow

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading

    val lists = HashMap<Int, ListData>()

    init {
        getLists()
    }


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

    fun updateList(listData: ListData) {
        viewModelScope.launch {
            // Update the repository
            listsRepository.updateList(
                list_id = listData.list_id,
                user_id = listData.user_id, // Assuming you need to pass this based on your repository method signature
                name = listData.name,
                is_complete = listData.is_complete
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
            is_complete = this.is_complete
        )
    }
}

