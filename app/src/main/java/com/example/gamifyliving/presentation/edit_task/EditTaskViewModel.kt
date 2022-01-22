package com.example.gamifyliving.presentation.edit_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gamifyliving.domain.repository.TaskRepository

class EditTaskViewModel(
    private val taskRepository: TaskRepository
) : ViewModel() {

    var name by mutableStateOf("")
        private set

    fun onNameChange(newName: String) {
        name = newName
    }

    fun onDelete() {

    }

    fun onSaveClicked() {

    }

}

class EditTaskViewModelFactory(
    private val taskRepository: TaskRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditTaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EditTaskViewModel(taskRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
