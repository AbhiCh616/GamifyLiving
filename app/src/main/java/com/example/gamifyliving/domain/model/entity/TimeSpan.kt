package com.example.gamifyliving.domain.model.entity

import com.example.gamifyliving.domain.exception.StartTimeGreaterThanEndTimeException
import java.time.LocalTime

data class TimeSpan(
    val startTime: LocalTime,
    val endTime: LocalTime
) {
    init {
        if(startTime > endTime) {
            throw StartTimeGreaterThanEndTimeException(startTime, endTime)
        }
    }
}
