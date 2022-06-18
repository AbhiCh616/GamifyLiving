package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.read_model.TaskWithDetailsEntity
import com.example.gamifyliving.data.data_source.local.model.table.HabitEntity
import com.example.gamifyliving.domain.model.entity.Habit

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

fun Habit.toHabitEntity(taskId: Int? = null) =
    HabitEntity(
        taskId = taskId ?: id
    )
