package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.table.EverydayScheduleEntity
import com.example.gamifyliving.domain.model.value_object.EverydaySchedule

fun EverydayScheduleEntity.toEverydaySchedule() =
    EverydaySchedule(
        timeSpan = timeSpan?.toTimeSpan()
    )

fun EverydaySchedule.toEverydayScheduleEntity(habitId: Int) =
    EverydayScheduleEntity(
        timeSpan = this.timeSpan?.toTimeSpanEntity(),
        habitId = habitId
    )
