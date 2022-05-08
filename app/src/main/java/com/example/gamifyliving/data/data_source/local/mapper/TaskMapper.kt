package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.*
import com.example.gamifyliving.domain.model.*

fun Task.toTaskEntity() =
    TaskEntity(
        name = this.name,
        status = this.status,
        id = this.id
    )

fun Todo.toTodoEntity(taskId: Int) =
    TodoEntity(
        coinsReward = this.coinsReward,
        taskId = taskId,
        id = this.id
    )

fun Habit.toHabitEntity(taskId: Int) =
    HabitEntity(
        taskId = taskId,
        id = this.id
    )

fun TimeSpan?.toTimeSpanEntity() =
    this?.let { timeSpan ->
        TimeSpanEntity(
            startTime = timeSpan.startTime,
            endTime = timeSpan.endTime
        )
    }

fun DateSchedule.toTodoScheduleEntity(todoId: Int) =
    TodoScheduleEntity(
        scheduledDate = this.date,
        timeSpan = this.timeSpan.toTimeSpanEntity(),
        todoId = todoId
    )

fun EverydaySchedule.toEverydayScheduleEntity(habitId: Int) =
    EverydayScheduleEntity(
        timeSpan = this.timeSpan.toTimeSpanEntity(),
        habitId = habitId
    )

fun RepeatAfterSchedule.toRepeatAfterScheduleEntity(habitId: Int) =
    RepeatAfterScheduleEntity(
        startDate = this.startDate,
        repeatAfter = this.interval,
        timeSpan = this.timeSpan.toTimeSpanEntity(),
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
        timeSpan = this.timeSpan.toTimeSpanEntity(),
        habitId = habitId
    )

fun TimeSpanEntity?.toTimeSpan() = this?.let { timeSpanEntity ->
    TimeSpan(
        startTime = timeSpanEntity.startTime,
        endTime = timeSpanEntity.endTime
    )
}

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
            id = this.task.id
        )
        this.habitWithSchedule != null -> Habit(
            name = this.task.name,
            schedule =
            when {
                habitWithSchedule.everydaySchedule != null -> EverydaySchedule(
                    timeSpan = habitWithSchedule.everydaySchedule.timeSpan.toTimeSpan()
                )
                habitWithSchedule.repeatAfterSchedule != null ->
                    habitWithSchedule.repeatAfterSchedule.let { repeatAfterScheduleEntity ->
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
            status = task.status,
            id = task.id
        )
        else -> throw TypeCastException()
    }

fun List<TaskWithDetailsEntity>.toDomainModel() =
    this.map { taskWithDetailsEntity -> taskWithDetailsEntity.toDomainModel() }
