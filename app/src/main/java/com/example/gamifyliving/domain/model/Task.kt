package com.example.gamifyliving.domain.model

import java.time.LocalDate

data class Task(
    val name: String,
    val status: Boolean = false,
    val coinsReward: Int = 0,
    val uid: Int = 0,
    val date: LocalDate? = null,
)
