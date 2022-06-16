package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.embedded.TimeSpanEntity
import com.example.gamifyliving.domain.model.value_object.TimeSpan

fun TimeSpanEntity.toTimeSpan() =
    TimeSpan(
        startTime = startTime,
        endTime = endTime
    )

fun TimeSpan.toTimeSpanEntity() =
    TimeSpanEntity(
        startTime = startTime,
        endTime = endTime
    )
