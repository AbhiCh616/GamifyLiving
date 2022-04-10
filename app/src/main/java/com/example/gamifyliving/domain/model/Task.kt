package com.example.gamifyliving.domain.model

import java.time.LocalDate
import java.time.LocalTime

data class Task(
    val name: String,
    val status: Boolean = false,
    val coinsReward: Int = 0,
    val uid: Int = 0,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val startTime: LocalTime? = null,
    val endTime: LocalTime? = null
)
