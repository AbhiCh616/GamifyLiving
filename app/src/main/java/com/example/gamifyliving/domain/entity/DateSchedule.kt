package com.example.gamifyliving.domain.entity

import java.time.LocalDate

data class DateSchedule(
    val date: LocalDate,
    override val timeSpan: TimeSpan? = null
) : Schedule
