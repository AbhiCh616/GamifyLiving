package com.example.gamifyliving.ui.screen.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.domain.repository.TaskRepository
import kotlinx.coroutines.launch

class TasksViewModel(
    private val taskRepository: TaskRepository
) : ViewModel() {

    val tasks = taskRepository.observeTasks()

    fun changeTaskStatus(task: Task) = viewModelScope.launch {
        val newTask = task.copy(status = !task.status)
        taskRepository.updateTask(task = newTask)
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
