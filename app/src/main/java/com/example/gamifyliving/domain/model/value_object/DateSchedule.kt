package com.example.gamifyliving.domain.model.value_object

import java.time.LocalDate

data class DateSchedule(
    val date: LocalDate,
    override val timeSpan: TimeSpan? = null
) : Schedule
