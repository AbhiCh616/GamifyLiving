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
import com.example.gamifyliving.presentation.util.toDateString
import com.example.gamifyliving.presentation.util.toLocalDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalTime
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

    private var selectedTask: Task? = null

    var name by mutableStateOf("")
        private set

    var coins by mutableStateOf("")
        private set

    var scheduledDate: String? by mutableStateOf(null)
        private set

    var startTime: String? by mutableStateOf(null)
        private set

    var endTime: String? by mutableStateOf(null)
        private set

    private val _rewards = mutableStateListOf<Reward>()

    val rewards: List<Reward>
        get() = _rewards

    val stats = getStats()

    private var numOfNewReward = 0

    init {
        savedStateHandle.get<Int>("task_id")?.let { taskId ->
            viewModelScope.launch {
                getTaskById(taskId)?.let { task ->
                    selectedTask = task
                    name = task.name
                    coins = task.coinsReward.toString()
                    scheduledDate = task.scheduledDate.toDateString()
                    startTime = task.startTime?.toString()
                    endTime = task.endTime?.toString()
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

    fun onCoinsChange(updatedCoins: String) {
        coins = updatedCoins
    }

    fun onScheduledDateChange(dateLong: Long?) {
        scheduledDate = dateLong.toDateString()
    }

    fun onDateClear() {
        scheduledDate = null
    }

    fun onStartTimeChange(updatedStartTime: LocalTime?) {
        startTime = updatedStartTime.toString()
    }

    fun onEndTimeChange(updatedEndTime: LocalTime?) {
        endTime = updatedEndTime.toString()
    }

    fun onTimeClear() {
        startTime = null
        endTime = null
    }

    fun onSaveClicked() = viewModelScope.launch {
        val updatedTask = selectedTask?.copy(
            name = name,
            coinsReward = coins.toInt(),
            scheduledDate = scheduledDate.toLocalDate(),
            startTime = startTime?.let { LocalTime.parse(startTime) },
            endTime = endTime?.let { LocalTime.parse(endTime) }
        )
        updatedTask?.let { updateTask(it, rewards) }
    }

    fun onDeleteClicked() = viewModelScope.launch {
        selectedTask?.let { deleteTask(it) }
    }

    fun editReward(updatedReward: Reward) {
        val rewardToChange = rewards.single { it.uid == updatedReward.uid }
        val rewardIndex = rewards.indexOf(rewardToChange)
        _rewards[rewardIndex] = updatedReward
    }

    fun addNewReward() = viewModelScope.launch {
        val newReward = Reward(
            taskId = selectedTask!!.uid,
            statId = stats.first().elementAt(0).uid,
            points = 0,
            uid = numOfNewReward
        )
        _rewards.add(newReward)
        numOfNewReward++
    }

    fun onDeleteReward(reward: Reward) {
        _rewards.remove(reward)
    }

}
