package com.example.gamifyliving.presentation.add_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.domain.repository.TaskRepository
import kotlinx.coroutines.launch

class AddTaskViewModel(
    private val taskRepository: TaskRepository
) : ViewModel() {

    var name by mutableStateOf("")
        private set

    fun onNameChange(newName: String) {
        name = newName
    }

    fun onSaveClicked() = viewModelScope.launch {
        taskRepository.addTask(Task(name = name))
    }

}

class AddTaskViewModelFactory(
    private val taskRepository: TaskRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddTaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddTaskViewModel(taskRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
