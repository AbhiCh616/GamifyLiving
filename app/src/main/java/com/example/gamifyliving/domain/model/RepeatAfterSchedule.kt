package com.example.gamifyliving.domain.model

import java.time.LocalDate

data class RepeatAfterSchedule(
    val startDate: LocalDate,
    val interval: Int,
    override val timeSpan: TimeSpan? = null
) : Schedule