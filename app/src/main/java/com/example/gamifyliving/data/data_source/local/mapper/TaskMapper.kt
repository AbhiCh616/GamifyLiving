package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.*
import com.example.gamifyliving.domain.model.entity.*

fun Task.toTaskEntity() =
    TaskEntity(
        name = this.name,
        status = this.status,
        id = this.id
    )

fun Todo.toTodoEntity(taskId: Int, todoId: Int = 0) =
    TodoEntity(
        coinsReward = this.coinsReward,
        taskId = taskId,
        id = todoId
    )

fun Habit.toHabitEntity(taskId: Int, habitId: Int = 0) =
    HabitEntity(
        taskId = taskId,
        id = habitId
    )

fun TimeSpan?.toTimeSpanEntity() =
    this?.let { timeSpan ->
        TimeSpanEntity(
            startTime = timeSpan.startTime,
            endTime = timeSpan.endTime
        )
    }

fun DateSchedule.toTodoScheduleEntity(todoId: Int, todoScheduleId: Int = 0) =
    TodoScheduleEntity(
        scheduledDate = this.date,
        timeSpan = this.timeSpan.toTimeSpanEntity(),
        todoId = todoId,
        id = todoScheduleId
    )

fun EverydaySchedule.toEverydayScheduleEntity(habitId: Int, everydayScheduleId: Int = 0) =
    EverydayScheduleEntity(
        timeSpan = this.timeSpan.toTimeSpanEntity(),
        habitId = habitId,
        id = everydayScheduleId
    )

fun RepeatAfterSchedule.toRepeatAfterScheduleEntity(habitId: Int, repeatScheduleId: Int = 0) =
    RepeatScheduleEntity(
        startDate = this.startDate,
        repeatAfter = this.interval,
        timeSpan = this.timeSpan.toTimeSpanEntity(),
        habitId = habitId,
        id = repeatScheduleId
    )

fun WeekDaySchedule.toWeekDayScheduleEntity(habitId: Int, weekDayScheduleId: Int = 0) =
    WeekDayScheduleEntity(
        sunday = this.sunday,
        monday = this.monday,
        tuesday = this.tuesday,
        wednesday = this.wednesday,
        thursday = this.thursday,
        friday = this.friday,
        saturday = this.saturday,
        timeSpan = this.timeSpan.toTimeSpanEntity(),
        habitId = habitId,
        id = weekDayScheduleId
    )

fun TimeSpanEntity?.toTimeSpan() = this?.let { timeSpanEntity ->
    TimeSpan(
        startTime = timeSpanEntity.startTime,
        endTime = timeSpanEntity.endTime
    )
}

fun Task.toRewardDataModel() =
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

fun RewardEntity.toDomainModel() =
    Reward(
        statId = this.statId,
        points = this.points
    )


fun TaskWithDetailsEntity.toDomainModel(): Task =
    when {
        this.todoWithSchedule != null -> Todo(
            name = this.task.name,
            coinsReward = todoWithSchedule.todo.coinsReward,
            schedule = todoWithSchedule.todoSchedule?.let { todoScheduleEntity ->
                DateSchedule(
                    date = todoScheduleEntity.scheduledDate,
                    timeSpan = todoScheduleEntity.timeSpan.toTimeSpan()
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
                    timeSpan = habitWithSchedule.everydaySchedule.timeSpan.toTimeSpan()
                )
                habitWithSchedule.repeatSchedule != null ->
                    habitWithSchedule.repeatSchedule.let { repeatAfterScheduleEntity ->
                        RepeatAfterSchedule(
                            startDate = repeatAfterScheduleEntity.startDate,
                            interval = repeatAfterScheduleEntity.repeatAfter,
                            timeSpan = repeatAfterScheduleEntity.timeSpan.toTimeSpan()
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
                            timeSpan = weekDayScheduleEntity.timeSpan.toTimeSpan()
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
