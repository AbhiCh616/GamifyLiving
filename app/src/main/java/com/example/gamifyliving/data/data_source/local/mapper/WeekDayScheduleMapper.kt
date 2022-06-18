package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.table.WeekDayScheduleEntity
import com.example.gamifyliving.domain.model.value_object.WeekDaySchedule

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
