package com.example.gamifyliving.domain.model

data class EverydaySchedule(
    override val timeSpan: TimeSpan? = null
) : Schedule
