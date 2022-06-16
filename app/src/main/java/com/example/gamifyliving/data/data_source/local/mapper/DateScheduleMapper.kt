package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.table.DateScheduleEntity
import com.example.gamifyliving.domain.model.value_object.DateSchedule

fun DateScheduleEntity.toDateSchedule() =
    DateSchedule(
        date = scheduledDate,
        timeSpan = timeSpan?.toTimeSpan()
    )

fun DateSchedule.toDateScheduleEntity(todoId: Int) =
    DateScheduleEntity(
        scheduledDate = this.date,
        timeSpan = this.timeSpan?.toTimeSpanEntity(),
        todoId = todoId
    )
