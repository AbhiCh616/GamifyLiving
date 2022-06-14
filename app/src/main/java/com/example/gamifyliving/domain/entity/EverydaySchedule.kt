package com.example.gamifyliving.domain.entity

data class EverydaySchedule(
    override val timeSpan: TimeSpan? = null
) : Schedule
