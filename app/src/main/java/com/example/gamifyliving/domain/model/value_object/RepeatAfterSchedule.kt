package com.example.gamifyliving.domain.model.value_object

import com.example.gamifyliving.domain.exception.DaysIntervalZeroOrNegativeException
import java.time.LocalDate

data class RepeatAfterSchedule(
    val startDate: LocalDate,
    val interval: Int,
    override val timeSpan: TimeSpan? = null
) : Schedule {
    init {
        if (interval <= 0) {
            throw DaysIntervalZeroOrNegativeException(interval)
        }
    }
}
