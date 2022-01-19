package com.example.gamifyliving.viewmodel

import androidx.lifecycle.*
import com.example.gamifyliving.data.model.Task
import com.example.gamifyliving.repository.RewardRepository
import com.example.gamifyliving.repository.TaskRepository
import kotlinx.coroutines.launch

class TasksViewModel(
    private val taskRepository: TaskRepository,
    private val rewardRepository: RewardRepository
) : ViewModel() {

    fun addTask(task: Task) = viewModelScope.launch {
        taskRepository.addTask(task)
    }

    private fun updateTask(task: Task) = viewModelScope.launch {
        taskRepository.updateTask(task)
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        taskRepository.deleteTask(task)
    }

    fun updateTaskName(task: Task, taskName: String) {
        val newTask = task.copy(name = taskName)
        updateTask(newTask)
    }

    fun changeTaskStatus(task: Task) {
        val newTask = task.copy(status = !task.status)
        updateTask(newTask)
    }

    fun getAllTasks(): LiveData<List<Task>> = taskRepository.observeTasks().asLiveData()

    /*fun addReward(reward: Reward) = viewModelScope.launch {
        rewardRepository.addReward(reward)
    }

    fun updateReward(reward: Reward) = viewModelScope.launch {
        rewardRepository.updateReward(reward)
    }

    fun deleteReward(reward: Reward) = viewModelScope.launch {
        rewardRepository.deleteReward(reward)
    }*/

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
