package com.example.gamifyliving.domain.model.value_object

data class EverydaySchedule(
    override val timeSpan: TimeSpan? = null
) : Schedule
