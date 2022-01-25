package com.example.gamifyliving.presentation.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    val tasks = taskRepository.observeTasks()

    fun changeTaskStatus(task: Task) = viewModelScope.launch {
        val newTask = task.copy(status = !task.status)
        taskRepository.updateTask(task = newTask)
    }

}
