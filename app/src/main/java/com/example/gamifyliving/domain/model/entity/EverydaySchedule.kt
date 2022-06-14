package com.example.gamifyliving.domain.model.entity

data class EverydaySchedule(
    override val timeSpan: TimeSpan? = null
) : Schedule
