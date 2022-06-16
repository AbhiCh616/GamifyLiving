package com.example.gamifyliving.data.data_source.local.model.read_model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.gamifyliving.data.data_source.local.model.table.RepeatScheduleEntity
import com.example.gamifyliving.data.data_source.local.model.table.WeekDayScheduleEntity
import com.example.gamifyliving.data.data_source.local.model.table.EverydayScheduleEntity
import com.example.gamifyliving.data.data_source.local.model.table.HabitEntity

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
