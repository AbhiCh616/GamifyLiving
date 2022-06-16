package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.read_model.HabitWithScheduleEntity

fun HabitWithScheduleEntity.toSchedule() =
    when {
        everydaySchedule != null -> everydaySchedule.toEverydaySchedule()
        repeatSchedule != null -> repeatSchedule.toRepeatSchedule()
        weekDaySchedule != null -> weekDaySchedule.toWeekDaySchedule()
        else -> null
    }
