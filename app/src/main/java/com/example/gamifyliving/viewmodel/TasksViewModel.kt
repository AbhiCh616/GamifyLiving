package com.example.gamifyliving.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.gamifyliving.data.model.Reward
import com.example.gamifyliving.data.model.Task
import com.example.gamifyliving.repository.RewardRepository
import com.example.gamifyliving.repository.TaskRepository
import kotlinx.coroutines.launch

class TasksViewModel(
    private val taskRepository: TaskRepository,
    private val rewardRepository: RewardRepository
) : ViewModel() {
    val tasks = taskRepository.allTasks.asLiveData()

    fun addTask(task: Task) = viewModelScope.launch {
        taskRepository.addTask(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        taskRepository.updateTask(task)
    }

    fun updateTaskValues(task: Task, taskName: String) {
        val newTask = task.copy(name = taskName)
        updateTask(newTask)
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        taskRepository.deleteTask(task)
    }

    fun changeTaskStatus(task: Task) = viewModelScope.launch {
        val newTask = task.copy(status = !task.status)
        updateTask(newTask)
    }

    fun addReward(reward: Reward) = viewModelScope.launch {
        rewardRepository.addReward(reward)
    }

    fun updateReward(reward: Reward) = viewModelScope.launch {
        rewardRepository.updateReward(reward)
    }

    fun deleteReward(reward: Reward) = viewModelScope.launch {
        rewardRepository.deleteReward(reward)
    }
}

class TasksViewModelFactory(
    private val taskRepository: TaskRepository,
    private val rewardRepository: RewardRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasksViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TasksViewModel(taskRepository, rewardRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}