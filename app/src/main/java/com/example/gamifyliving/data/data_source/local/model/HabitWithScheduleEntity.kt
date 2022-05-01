package com.example.gamifyliving.data.data_source.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class HabitWithScheduleEntity(
    @Embedded val habit: HabitEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "habit_id"
    )
    val everydaySchedule: EverydayScheduleEntity?,

    @Relation(
        parentColumn = "id",
        entityColumn = "habit_id"
    )
    val weekDaySchedule: WeekDayScheduleEntity?,

    @Relation(
        parentColumn = "id",
        entityColumn = "habit_id"
    )
    val repeatAfterSchedule: RepeatAfterScheduleEntity?
)
