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

fun Todo.toTodoEntity() =
    TodoEntity(
        coinsReward = coinsReward,
        taskId = id
    )

fun Habit.toHabitEntity() =
    HabitEntity(
        taskId = id
    )

fun TimeSpan.toTimeSpanEntity() =
    TimeSpanEntity(
        startTime = startTime,
        endTime = endTime
    )

fun DateSchedule.toDateScheduleEntity(todoId: Int) =
    DateScheduleEntity(
        scheduledDate = this.date,
        timeSpan = this.timeSpan?.toTimeSpanEntity(),
        todoId = todoId
    )

fun EverydaySchedule.toEverydayScheduleEntity(habitId: Int) =
    EverydayScheduleEntity(
        timeSpan = this.timeSpan?.toTimeSpanEntity(),
        habitId = habitId
    )

fun RepeatSchedule.toRepeatScheduleEntity(habitId: Int) =
    RepeatScheduleEntity(
        startDate = this.startDate,
        repeatAfter = this.interval,
        timeSpan = this.timeSpan?.toTimeSpanEntity(),
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
        timeSpan = this.timeSpan?.toTimeSpanEntity(),
        habitId = habitId
    )

fun TimeSpanEntity.toTimeSpan() =
    TimeSpan(
        startTime = startTime,
        endTime = endTime
    )



fun DateScheduleEntity.toDateSchedule() =
    DateSchedule(
        date = scheduledDate,
        timeSpan = timeSpan?.toTimeSpan()
    )

fun TaskWithDetailsEntity.toTodo(): Todo =
    Todo(
        id = task.id,
        name = task.name,
        coinsReward = todoWithSchedule!!.todo.coinsReward,
        schedule = todoWithSchedule.todoSchedule?.toDateSchedule(),
        status = task.status,
        rewards = rewards?.toRewardList()
    )

fun List<TaskWithDetailsEntity>.toTodoList(): List<Todo> =
    this.filter { taskWithDetailsEntity ->
        taskWithDetailsEntity.todoWithSchedule != null
    }.map { taskWithDetailsEntity ->
        taskWithDetailsEntity.toTodo()
    }

fun TaskWithDetailsEntity.toHabit() =
    Habit(
        id = task.id,
        name = task.name,
        status = task.status,
        rewards = rewards?.toRewardList(),
        schedule = habitWithSchedule!!.toSchedule()
    )

fun List<TaskWithDetailsEntity>.toHabitList() =
    this.filter { taskWithDetailsEntity ->
        taskWithDetailsEntity.habitWithSchedule != null
    }.map { taskWithDetailsEntity ->
        taskWithDetailsEntity.toHabit()
    }

fun HabitWithScheduleEntity.toSchedule(): Schedule? =
    when {
        everydaySchedule != null -> everydaySchedule.toEverydaySchedule()
        repeatSchedule != null -> repeatSchedule.toRepeatSchedule()
        weekDaySchedule != null -> weekDaySchedule.toWeekDaySchedule()
        else -> null
    }

fun EverydayScheduleEntity.toEverydaySchedule() =
    EverydaySchedule(
        timeSpan = timeSpan?.toTimeSpan()
    )

fun RepeatScheduleEntity.toRepeatSchedule() =
    RepeatSchedule(
        startDate = startDate,
        interval = repeatAfter,
        timeSpan = timeSpan?.toTimeSpan()
    )

fun WeekDayScheduleEntity.toWeekDaySchedule() =
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
