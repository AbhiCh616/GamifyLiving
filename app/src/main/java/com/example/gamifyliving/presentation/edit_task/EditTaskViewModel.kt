package com.example.gamifyliving.presentation.edit_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamifyliving.domain.model.*
import com.example.gamifyliving.domain.use_case.*
import com.example.gamifyliving.presentation.util.toDateString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDate
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

    private var scheduledDate: LocalDate? by mutableStateOf(null)

    val scheduledDateText
        get() = scheduledDate?.toDateString()

    private var startTime: LocalTime? by mutableStateOf(null)

    val startTimeText
        get() = startTime?.toString()

    private var endTime: LocalTime? by mutableStateOf(null)

    val endTimeText
        get() = endTime?.toString()

    private val _rewards = mutableStateListOf<Reward>()

    val rewards: List<Reward>
        get() = _rewards

    val stats = getStats()

    private var numOfNewReward = 0

    init {
        savedStateHandle.get<Int>("task_id")?.let { taskId ->
            viewModelScope.launch {
                getTaskById(taskId)?.let { task ->
                    task as Todo
                    selectedTask = task
                    name = task.name
                    coins = task.coinsReward.toString()
                    scheduledDate = task.schedule?.date
                    startTime = task.schedule?.timeSpan?.startTime
                    endTime = task.schedule?.timeSpan?.startTime
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

    fun onScheduledDateChange(dateLong: LocalDate?) {
        scheduledDate = dateLong
    }

    fun onDateClear() {
        scheduledDate = null
    }

    fun onStartTimeChange(updatedStartTime: LocalTime?) {
        startTime = updatedStartTime
    }

    fun onEndTimeChange(updatedEndTime: LocalTime?) {
        endTime = updatedEndTime
    }

    fun onTimeClear() {
        startTime = null
        endTime = null
    }

    fun onSaveClicked() = viewModelScope.launch {
        val updatedTask = (selectedTask as Todo).copy(
            name = name,
            coinsReward = coins.toInt(),
            schedule = scheduledDate?.let {
                DateSchedule(
                    date = it,
                    timeSpan = startTime?.let { startTime ->
                        TimeSpan(
                            startTime = startTime,
                            endTime = endTime!!
                        )
                    }
                )
            }
        )
        updateTask(updatedTask, rewards)
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
            taskId = selectedTask!!.id,
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
