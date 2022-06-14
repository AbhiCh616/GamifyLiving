package com.example.gamifyliving.presentation.add_edit_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamifyliving.domain.model.*
import com.example.gamifyliving.application.use_case.*
import com.example.gamifyliving.presentation.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val getTaskById: GetTaskById,
    private val deleteTask: DeleteTask,
    private val updateTask: UpdateTask,
    private val addTask: AddTask,
    getStats: GetStats,
    getRewardsForTask: GetRewardsForTask,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var selectedTask: Task? = null

    val isNewTask
        get() = selectedTask == null

    private val selectedTaskType
        get() = when (selectedTask) {
            is Todo -> TaskType.TODO
            is Habit -> TaskType.HABIT
            else -> null
        }

    private var newTaskType by mutableStateOf(TaskType.TODO)

    val taskType
        get() = selectedTaskType ?: newTaskType

    var name by mutableStateOf("")
        private set

    var coins by mutableStateOf("")
        private set

    var scheduleType: ScheduleType? by mutableStateOf(null)
        private set

    var isScheduleDropdownExpanded by mutableStateOf(false)
        private set

    var repeatInterval by mutableStateOf("")
        private set

    var daysOfWeek: DaysOfWeek by mutableStateOf(DaysOfWeek())
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
                getTaskById(taskId)
                    .onSuccess { task ->
                        task?.let {
                            when (task) {
                                is Todo -> {
                                    selectedTask = task
                                    name = task.name
                                    coins = task.coinsReward.toString()
                                    scheduledDate = task.schedule?.date
                                    startTime = task.schedule?.timeSpan?.startTime
                                    endTime = task.schedule?.timeSpan?.endTime
                                    getRewardsForTask(task).collect {
                                        it.forEach { reward ->
                                            _rewards.add(reward)
                                        }
                                    }
                                }
                                is Habit -> {
                                    selectedTask = task
                                    name = task.name
                                    scheduleType = when (task.schedule) {
                                        is EverydaySchedule -> ScheduleType.EVERYDAY
                                        is RepeatAfterSchedule -> {
                                            repeatInterval =
                                                (task.schedule as RepeatAfterSchedule).interval.toString()
                                            ScheduleType.REPEAT_AFTER
                                        }
                                        is WeekDaySchedule -> {
                                            daysOfWeek =
                                                (task.schedule as WeekDaySchedule).let {
                                                    DaysOfWeek(
                                                        sunday = it.sunday,
                                                        monday = it.monday,
                                                        tuesday = it.tuesday,
                                                        wednesday = it.wednesday,
                                                        thursday = it.thursday,
                                                        friday = it.friday,
                                                        saturday = it.saturday
                                                    )
                                                }
                                            ScheduleType.DAY_OF_WEEK
                                        }
                                        else -> null
                                    }
                                    startTime = task.schedule?.timeSpan?.startTime
                                    endTime = task.schedule?.timeSpan?.endTime
                                    getRewardsForTask(task).collect {
                                        it.forEach { reward ->
                                            _rewards.add(reward)
                                        }
                                    }
                                }
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

    fun onTaskTypeChange(taskType: TaskType) {
        newTaskType = taskType
    }

    fun onScheduleTypeChange(scheduleType: ScheduleType?) {
        this.scheduleType = scheduleType
    }

    fun scheduleDropdownExpandedChange(currentState: Boolean) {
        isScheduleDropdownExpanded = !isScheduleDropdownExpanded
    }

    fun dismissScheduleDropdown() {
        isScheduleDropdownExpanded = false
    }

    fun onScheduledDateChange(dateLong: LocalDate?) {
        scheduledDate = dateLong
    }

    fun onDateClear() {
        scheduledDate = null
    }

    fun onRepeatIntervalChange(interval: String) {
        repeatInterval = interval
    }

    fun onDaysOfWeekSelectionChange(weekDay: WeekDay) {
        daysOfWeek = when (weekDay) {
            WeekDay.SUNDAY -> daysOfWeek.copy(sunday = !daysOfWeek.sunday)
            WeekDay.MONDAY -> daysOfWeek.copy(monday = !daysOfWeek.monday)
            WeekDay.TUESDAY -> daysOfWeek.copy(tuesday = !daysOfWeek.tuesday)
            WeekDay.WEDNESDAY -> daysOfWeek.copy(wednesday = !daysOfWeek.wednesday)
            WeekDay.THURSDAY -> daysOfWeek.copy(thursday = !daysOfWeek.thursday)
            WeekDay.FRIDAY -> daysOfWeek.copy(friday = !daysOfWeek.friday)
            WeekDay.SATURDAY -> daysOfWeek.copy(saturday = !daysOfWeek.saturday)
        }
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
        if (!isNewTask) {
            selectedTask.let { task ->
                val updatedTask = when (task) {
                    is Todo -> {
                        (selectedTask as Todo).copy(
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
                    }
                    is Habit -> {
                        (selectedTask as Habit).copy(
                            name = name,
                            schedule = when (scheduleType) {
                                ScheduleType.EVERYDAY -> EverydaySchedule(
                                    timeSpan = startTime?.let {
                                        TimeSpan(
                                            startTime = startTime!!,
                                            endTime = endTime!!,
                                        )
                                    }
                                )
                                ScheduleType.REPEAT_AFTER -> RepeatAfterSchedule(
                                    startDate = LocalDate.now(),
                                    interval = repeatInterval.toInt(),
                                    timeSpan = startTime?.let {
                                        TimeSpan(
                                            startTime = startTime!!,
                                            endTime = endTime!!,
                                        )
                                    }
                                )
                                ScheduleType.DAY_OF_WEEK -> WeekDaySchedule(
                                    sunday = daysOfWeek.sunday,
                                    monday = daysOfWeek.monday,
                                    tuesday = daysOfWeek.tuesday,
                                    wednesday = daysOfWeek.wednesday,
                                    thursday = daysOfWeek.thursday,
                                    friday = daysOfWeek.friday,
                                    saturday = daysOfWeek.saturday,
                                    timeSpan = startTime?.let {
                                        TimeSpan(
                                            startTime = startTime!!,
                                            endTime = endTime!!,
                                        )
                                    }
                                )
                                null -> null
                            },
                        )
                    }
                    else -> throw TypeCastException()
                }
                updateTask(updatedTask, rewards)
            }
        } else {
            val newTask = when (taskType) {
                TaskType.TODO -> {
                    Todo(
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
                }
                TaskType.HABIT -> {
                    Habit(
                        name = name,
                        schedule = when (scheduleType) {
                            ScheduleType.EVERYDAY -> EverydaySchedule(
                                timeSpan = startTime?.let {
                                    TimeSpan(
                                        startTime = startTime!!,
                                        endTime = endTime!!,
                                    )
                                }
                            )
                            ScheduleType.REPEAT_AFTER -> RepeatAfterSchedule(
                                startDate = LocalDate.now(),
                                interval = repeatInterval.toInt(),
                                timeSpan = startTime?.let {
                                    TimeSpan(
                                        startTime = startTime!!,
                                        endTime = endTime!!,
                                    )
                                }
                            )
                            ScheduleType.DAY_OF_WEEK -> WeekDaySchedule(
                                sunday = daysOfWeek.sunday,
                                monday = daysOfWeek.monday,
                                tuesday = daysOfWeek.tuesday,
                                wednesday = daysOfWeek.wednesday,
                                thursday = daysOfWeek.thursday,
                                friday = daysOfWeek.friday,
                                saturday = daysOfWeek.saturday,
                                timeSpan = startTime?.let {
                                    TimeSpan(
                                        startTime = startTime!!,
                                        endTime = endTime!!,
                                    )
                                }
                            )
                            null -> null
                        },
                    )
                }
            }
            addTask(newTask, rewards)
        }
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
            taskId = selectedTask?.id ?: 0,
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
