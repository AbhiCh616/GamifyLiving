package com.example.gamifyliving.domain.model

data class WeekDaySchedule(
    val sunday: Boolean = false,
    val monday: Boolean = false,
    val tuesday: Boolean = false,
    val wednesday: Boolean = false,
    val thursday: Boolean = false,
    val friday: Boolean = false,
    val saturday: Boolean = false,
    override val timeSpan: TimeSpan? = null,
) : Schedule
