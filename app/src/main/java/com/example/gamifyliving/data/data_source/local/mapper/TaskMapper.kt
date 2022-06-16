package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.embedded.TimeSpanEntity
import com.example.gamifyliving.data.data_source.local.model.read_model.HabitWithScheduleEntity
import com.example.gamifyliving.data.data_source.local.model.read_model.TaskWithDetailsEntity
import com.example.gamifyliving.data.data_source.local.model.table.*
import com.example.gamifyliving.domain.model.entity.Habit
import com.example.gamifyliving.domain.model.entity.Reward
import com.example.gamifyliving.domain.model.entity.Task
import com.example.gamifyliving.domain.model.entity.Todo
import com.example.gamifyliving.domain.model.value_object.*

fun Task.toTaskEntity() =
    TaskEntity(
        name = name,
        status = status,
        id = id
    )


fun Todo.toDataModel() =
    TodoEntity(
        coinsReward = coinsReward,
        taskId = id
    )

fun Habit.toDataModel() =
    HabitEntity(
        taskId = id
    )

fun TimeSpan.toDataModel() =
    TimeSpanEntity(
        startTime = startTime,
        endTime = endTime
    )

fun DateSchedule.toDataModel(todoId: Int) =
    DateScheduleEntity(
        scheduledDate = this.date,
        timeSpan = this.timeSpan?.toDataModel(),
        todoId = todoId
    )

fun EverydaySchedule.toEverydayScheduleEntity(habitId: Int) =
    EverydayScheduleEntity(
        timeSpan = this.timeSpan?.toDataModel(),
        habitId = habitId
    )

fun RepeatSchedule.toRepeatAfterScheduleEntity(habitId: Int) =
    RepeatScheduleEntity(
        startDate = this.startDate,
        repeatAfter = this.interval,
        timeSpan = this.timeSpan?.toDataModel(),
        habitId = habitId
    )

fun WeekDaySchedule.toWeekDayScheduleEntity(habitId: Int) =
    WeekDayScheduleEntity(
        sunday = this.sunday,
        monday = this.monday,
        tuesday = this.tuesday,
        wednesday = this.wednesday,
        thursday = this.thursday,
        friday = this.friday,
        saturday = this.saturday,
        timeSpan = this.timeSpan?.toDataModel(),
        habitId = habitId
    )

fun TimeSpanEntity.toTimeSpan() =
    TimeSpan(
        startTime = startTime,
        endTime = endTime
    )


fun Task.toDataModel() =
    this.let {
        val list = mutableListOf<RewardEntity>()
        for (reward in this.rewards!!) {
            list.add(
                RewardEntity(
                    taskId = this.id,
                    statId = reward.statId,
                    points = reward.points
                )
            )
        }
        list.toList()
    }

fun Reward.toDataModel(taskId: Int) =
    RewardEntity(
        taskId = taskId,
        statId = statId,
        points = points
    )

fun List<Reward>.toDataModel(taskId: Int) =
    this.map { reward -> reward.toDataModel(taskId) }

fun RewardEntity.toDomainModel() =
    Reward(
        statId = this.statId,
        points = this.points
    )

fun DateScheduleEntity.toDomainModel() =
    DateSchedule(
        date = scheduledDate,
        timeSpan = timeSpan?.toTimeSpan()
    )

fun TaskWithDetailsEntity.toTodoModel(): Todo =
    Todo(
        id = task.id,
        name = task.name,
        coinsReward = todoWithSchedule!!.todo.coinsReward,
        schedule = todoWithSchedule.todoSchedule?.toDomainModel(),
        status = task.status,
        rewards = rewards?.toDomainModel()
    )

fun List<TaskWithDetailsEntity>.toTodoModel(): List<Todo> =
    this.filter { taskWithDetailsEntity ->
        taskWithDetailsEntity.todoWithSchedule != null
    }.map { taskWithDetailsEntity ->
        taskWithDetailsEntity.toTodoModel()
    }

fun TaskWithDetailsEntity.toHabitModel() =
    Habit(
        id = task.id,
        name = task.name,
        status = task.status,
        rewards = rewards?.toDomainModel(),
        schedule = habitWithSchedule!!.toScheduleModel()
    )

fun List<TaskWithDetailsEntity>.toHabitModel() =
    this.filter { taskWithDetailsEntity ->
        taskWithDetailsEntity.habitWithSchedule != null
    }.map { taskWithDetailsEntity ->
        taskWithDetailsEntity.toHabitModel()
    }

fun HabitWithScheduleEntity.toScheduleModel(): Schedule? =
    when {
        everydaySchedule != null -> everydaySchedule.toDomainModel()
        repeatSchedule != null -> repeatSchedule.toDomainModel()
        weekDaySchedule != null -> weekDaySchedule.toDomainModel()
        else -> null
    }

fun EverydayScheduleEntity.toDomainModel() =
    EverydaySchedule(
        timeSpan = timeSpan?.toTimeSpan()
    )

fun RepeatScheduleEntity.toDomainModel() =
    RepeatSchedule(
        startDate = startDate,
        interval = repeatAfter,
        timeSpan = timeSpan?.toTimeSpan()
    )

fun WeekDayScheduleEntity.toDomainModel() =
    WeekDaySchedule(
        sunday = sunday,
        monday = monday,
        tuesday = tuesday,
        wednesday = wednesday,
        thursday = thursday,
        friday = friday,
        saturday = saturday,
        timeSpan = timeSpan?.toTimeSpan()
    )

fun TaskWithDetailsEntity.toDomainModel(): Task =
    when {
        this.todoWithSchedule != null -> Todo(
            name = this.task.name,
            coinsReward = todoWithSchedule.todo.coinsReward,
            schedule = todoWithSchedule.todoSchedule?.let { todoScheduleEntity ->
                DateSchedule(
                    date = todoScheduleEntity.scheduledDate,
                    timeSpan = todoScheduleEntity.timeSpan?.toTimeSpan()
                )
            },
            status = this.task.status,
            rewards = this.rewards?.toDomainModel(),
            id = this.task.id
        )
        this.habitWithSchedule != null -> Habit(
            name = this.task.name,
            schedule =
            when {
                habitWithSchedule.everydaySchedule != null -> EverydaySchedule(
                    timeSpan = habitWithSchedule.everydaySchedule.timeSpan?.toTimeSpan()
                )
                habitWithSchedule.repeatSchedule != null ->
                    habitWithSchedule.repeatSchedule.let { repeatAfterScheduleEntity ->
                        RepeatSchedule(
                            startDate = repeatAfterScheduleEntity.startDate,
                            interval = repeatAfterScheduleEntity.repeatAfter,
                            timeSpan = repeatAfterScheduleEntity.timeSpan?.toTimeSpan()
                        )
                    }
                habitWithSchedule.weekDaySchedule != null ->
                    habitWithSchedule.weekDaySchedule.let { weekDayScheduleEntity ->
                        WeekDaySchedule(
                            sunday = weekDayScheduleEntity.sunday,
                            monday = weekDayScheduleEntity.monday,
                            tuesday = weekDayScheduleEntity.tuesday,
                            wednesday = weekDayScheduleEntity.wednesday,
                            thursday = weekDayScheduleEntity.thursday,
                            friday = weekDayScheduleEntity.friday,
                            saturday = weekDayScheduleEntity.saturday,
                            timeSpan = weekDayScheduleEntity.timeSpan?.toTimeSpan()
                        )
                    }
                else -> null
            },
            rewards = this.rewards?.toDomainModel(),
            status = task.status,
            id = task.id
        )
        else -> throw TypeCastException()
    }

fun List<TaskWithDetailsEntity>.toDomainModel() =
    this.map { taskWithDetailsEntity -> taskWithDetailsEntity.toDomainModel() }
