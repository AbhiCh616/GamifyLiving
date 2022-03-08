package com.example.gamifyliving.presentation.edit_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamifyliving.domain.model.Reward
import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.domain.use_case.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    private val getTaskById: GetTaskById,
    private val deleteTask: DeleteTask,
    private val updateTask: UpdateTask,
    getStats: GetStats,
    getRewardsForTask: GetRewardsForTask,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var name by mutableStateOf("")
        private set

    private var selectedTask: Task? = null

    val stats = getStats()

    private val _rewards = mutableStateListOf<Reward>()

    val rewards: List<Reward>
        get() = _rewards

    var coins: Int? by mutableStateOf(0)
        private set

    init {
        savedStateHandle.get<Int>("task_id")?.let { taskId ->
            viewModelScope.launch {
                getTaskById(taskId)?.let { task ->
                    selectedTask = task
                    name = task.name
                    getRewardsForTask(task).collect {
                        it.forEach { reward ->
                            _rewards.add(reward)
                        }
                    }
                }
            }
        }
    }

    fun onNameChange(newName: String) {
        name = newName
    }

    fun onDeleteClicked() = viewModelScope.launch {
        selectedTask?.let { deleteTask(it) }
    }

    fun onSaveClicked() = viewModelScope.launch {
        val updatedTask = selectedTask?.copy(name = name)
        updatedTask?.let { updateTask(it, rewards) }
    }

    fun editReward(updatedReward: Reward) {
        val reward = rewards.single { it.uid == updatedReward.uid }
        val rewardIndex = rewards.indexOf(reward)
        _rewards[rewardIndex] = updatedReward
    }

    fun addNewReward() = viewModelScope.launch {
        _rewards.add(
            Reward(
                taskId = selectedTask!!.uid,
                statId = stats.first().elementAt(0).uid,
                points = 0
            )
        )
    }

    fun onDeleteReward(reward: Reward) = viewModelScope.launch {
        _rewards.remove(reward)
    }

    fun onCoinsChange(updatedCoins: String) = viewModelScope.launch {
        coins = updatedCoins.toIntOrNull()
    }

}
