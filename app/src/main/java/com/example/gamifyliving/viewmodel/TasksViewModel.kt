package com.example.gamifyliving.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.gamifyliving.data.model.Task
import com.example.gamifyliving.repository.TaskRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TasksViewModel (
    private val taskRepository: TaskRepository
): ViewModel() {
    val tasks = taskRepository.allTasks.asLiveData()

    fun addTask(task: Task) = viewModelScope.launch {
        taskRepository.addTask(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        taskRepository.updateTask(task)
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        taskRepository.deleteTask(task)
    }
}

class TasksViewModelFactory(
    private val taskRepository: TaskRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasksViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TasksViewModel(taskRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}