package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.table.RepeatScheduleEntity
import com.example.gamifyliving.domain.model.value_object.RepeatSchedule

fun RepeatScheduleEntity.toRepeatSchedule() =
    RepeatSchedule(
        startDate = startDate,
        interval = repeatAfter,
        timeSpan = timeSpan?.toTimeSpan()
    )

fun RepeatSchedule.toRepeatScheduleEntity(habitId: Int) =
    RepeatScheduleEntity(
        startDate = this.startDate,
        repeatAfter = this.interval,
        timeSpan = this.timeSpan?.toTimeSpanEntity(),
        habitId = habitId
    )
