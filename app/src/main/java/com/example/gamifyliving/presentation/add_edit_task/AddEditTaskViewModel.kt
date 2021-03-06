package com.example.gamifyliving.presentation.add_edit_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamifyliving.domain.model.entity.Habit
import com.example.gamifyliving.domain.model.entity.Task
import com.example.gamifyliving.domain.model.entity.Todo
import com.example.gamifyliving.domain.model.value_object.*
import com.example.gamifyliving.domain.use_case.command.*
import com.example.gamifyliving.domain.use_case.query.GetStats
import com.example.gamifyliving.domain.use_case.query.GetTaskById
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
    private val deleteTodo: DeleteTodo,
    private val deleteHabit: DeleteHabit,
    private val updateTodo: UpdateTodo,
    private val updateHabit: UpdateHabit,
    private val addTodo: AddTodo,
    private val addHabit: AddHabit,
    getStats: GetStats,
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

    private val _rewards = mutableStateListOf<RewardUIModel>()

    val rewards: List<RewardUIModel>
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
                                    task.rewards?.let {
                                        var index = 0
                                        it.forEach { reward ->
                                            _rewards.add(reward.toUIModel(taskId, index))
                                            index++
                                        }
                                    }
                                }
                                is Habit -> {
                                    selectedTask = task
                                    name = task.name
                                    scheduleType = when (task.schedule) {
                                        is EverydaySchedule -> ScheduleType.EVERYDAY
                                        is RepeatSchedule -> {
                                            repeatInterval =
                                                (task.schedule as RepeatSchedule).interval.toString()
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
                                    task.rewards?.let {
                                        var index = 0
                                        it.forEach { reward ->
                                            _rewards.add(reward.toUIModel(taskId, index))
                                            index++
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
                        updateTodo(
                            (selectedTask as Todo).copy(
                                name = name,
                                rewards = rewards.toDomainModel(),
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
                        )
                    }
                    is Habit -> {
                        updateHabit(
                            (selectedTask as Habit).copy(
                                name = name,
                                rewards = rewards.toDomainModel(),
                                schedule = when (scheduleType) {
                                    ScheduleType.EVERYDAY -> EverydaySchedule(
                                        timeSpan = startTime?.let {
                                            TimeSpan(
                                                startTime = startTime!!,
                                                endTime = endTime!!,
                                            )
                                        }
                                    )
                                    ScheduleType.REPEAT_AFTER -> RepeatSchedule(
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
                        )
                    }
                    else -> throw TypeCastException()
                }
            }
        } else {
            val newTask = when (taskType) {
                TaskType.TODO -> {
                    val todo = Todo(
                        name = name,
                        coinsReward = coins.toInt(),
                        rewards = rewards.toDomainModel(),
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
                    addTodo(todo)
                }
                TaskType.HABIT -> {
                    val habit = Habit(
                        name = name,
                        rewards = rewards.toDomainModel(),
                        schedule = when (scheduleType) {
                            ScheduleType.EVERYDAY -> EverydaySchedule(
                                timeSpan = startTime?.let {
                                    TimeSpan(
                                        startTime = startTime!!,
                                        endTime = endTime!!,
                                    )
                                }
                            )
                            ScheduleType.REPEAT_AFTER -> RepeatSchedule(
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
                    addHabit(habit)
                }
            }
        }
    }


    fun onDeleteClicked() = viewModelScope.launch {
        selectedTask?.let {
            if (it is Todo) {
                deleteTodo(it)
            }
            if (it is Habit) {
                deleteHabit(it)
            }
        }
    }

    fun editReward(updatedReward: RewardUIModel) {
        val rewardToChange = rewards.single { it.uid == updatedReward.uid }
        val rewardIndex = rewards.indexOf(rewardToChange)
        _rewards[rewardIndex] = updatedReward
    }

    fun addNewReward() = viewModelScope.launch {
        val newReward = RewardUIModel(
            taskId = selectedTask?.id ?: 0,
            statId = stats.first().elementAt(0).id,
            points = 0,
            uid = numOfNewReward
        )
        _rewards.add(newReward)
        numOfNewReward++
    }

    fun onDeleteReward(reward: RewardUIModel) {
        _rewards.remove(reward)
    }

}
