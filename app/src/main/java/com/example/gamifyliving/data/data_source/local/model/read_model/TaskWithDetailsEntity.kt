package com.example.gamifyliving.data.data_source.local.model.read_model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.gamifyliving.data.data_source.local.model.table.*

data class TaskWithDetailsEntity(

    @Embedded val task: TaskEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "task_id",
        entity = TodoEntity::class
    )
    val todoWithSchedule: TodoWithScheduleEntity?,

    @Relation(
        parentColumn = "id",
        entityColumn = "task_id",
        entity = HabitEntity::class
    )
    val habitWithSchedule: HabitWithScheduleEntity?,

    @Relation(
        parentColumn = "id",
        entityColumn = "task_id",
        entity = RewardEntity::class
    )
    val rewards: List<RewardEntity>?

)

data class TodoWithScheduleEntity(

    @Embedded val todo: TodoEntity,

    @Relation(
        parentColumn = "task_id",
        entityColumn = "todo_id"
    )
    val todoSchedule: DateScheduleEntity?

)

data class HabitWithScheduleEntity(

    @Embedded val habit: HabitEntity,

    @Relation(
        parentColumn = "task_id",
        entityColumn = "habit_id"
    )
    val everydaySchedule: EverydayScheduleEntity?,

    @Relation(
        parentColumn = "task_id",
        entityColumn = "habit_id"
    )
    val weekDaySchedule: WeekDayScheduleEntity?,

    @Relation(
        parentColumn = "task_id",
        entityColumn = "habit_id"
    )
    val repeatSchedule: RepeatScheduleEntity?

)
